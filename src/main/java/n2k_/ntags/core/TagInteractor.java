package n2k_.ntags.core;
import n2k_.ntags.core.decorator.PAPIDecorator;
import n2k_.ntags.base.ADecorator;
import n2k_.ntags.base.IInteractor;
import n2k_.ntags.base.APresenter;
import n2k_.ntags.base.model.ConfigModel;
import n2k_.ntags.base.object.Line;
import n2k_.ntags.base.object.State;
import n2k_.ntags.core.decorator.NameDecorator;
import n2k_.ntags.core.presenter.CommandPresenter;
import n2k_.ntags.core.presenter.EventPresenter;
import n2k_.ntags.nTags;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
@SuppressWarnings("ALL")
public final class TagInteractor implements IInteractor {
    private static final String TEAM_NAME = "n_hidden_tags";
    private final Team TEAM;
    private final JavaPlugin PLUGIN;
    private final ArrayList<APresenter> PRESENTER_LIST;
    private final TagsRepository TAGS_REPOSITORY;
    public TagInteractor(@NotNull nTags PLUGIN) {
        this.TEAM = this.newTeam();
        this.PLUGIN = PLUGIN;
        this.PRESENTER_LIST = new ArrayList<>();
        this.TAGS_REPOSITORY = new TagsRepository(this);
    }
    @Override
    public void init() {
        this.PRESENTER_LIST.add(new EventPresenter(this));
        if(this.getConfig().ENABLE_COMMANDS) this.PRESENTER_LIST.add(new CommandPresenter(this));
        this.PRESENTER_LIST.forEach(APresenter::init);
        this.TAGS_REPOSITORY.init();
    }
    @Override
    public void sendStateAB(@NotNull Player PLAYER, Player CLICKED_PLAYER) {
        ConfigModel CONFIG_MODEL = this.getConfig();
        if(!CONFIG_MODEL.SEND_ACTION_BAR_FROM_DISPLAYED_PLAYERS && !this.getState(CLICKED_PLAYER).isHide()) return;
        ADecorator OBJECT = new PAPIDecorator(new NameDecorator(new Line(CLICKED_PLAYER, CONFIG_MODEL.MESSAGES.ACTION_BAR)));
        PLAYER.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(OBJECT.getContent()));
    }
    @Override
    public void loadPlayer(@NotNull Player PLAYER) {
        State STATE = this.getState(PLAYER);
        if(STATE.isHide()) {
            this.hidePlayerTag(PLAYER.getName(), false, true);
        } else {
            this.showPlayerTag(PLAYER.getName(), false, true);
        }
    }
    @Override
    public void hidePlayerTag(@Nullable String NAME, boolean IN_DATA, boolean IN_GAME) {
        Scoreboard SCOREBOARD = Bukkit.getScoreboardManager().getMainScoreboard();
        if(IN_GAME) SCOREBOARD.getTeam(TagInteractor.TEAM_NAME).addEntry(NAME);
        if(IN_DATA) this.TAGS_REPOSITORY.setValue(new State(NAME, true));
    }
    @Override
    public void showPlayerTag(@Nullable String NAME, boolean IN_DATA, boolean IN_GAME) {
        Scoreboard SCOREBOARD = Bukkit.getScoreboardManager().getMainScoreboard();
        if(IN_GAME) SCOREBOARD.getTeam(TagInteractor.TEAM_NAME).removeEntry(NAME);
        if(IN_DATA) this.TAGS_REPOSITORY.setValue(new State(NAME, false));
    }
    @NotNull
    public State getState(@NotNull Player PLAYER) {
        return this.TAGS_REPOSITORY.getValue(PLAYER.getName());
    }
    @Override
    public JavaPlugin getPlugin() {
        return this.PLUGIN;
    }
    @Contract(pure = true) @Override @Nullable
    public ConfigModel getConfig() {
        return ((nTags) this.PLUGIN).getConfigModel();
    }
    @NotNull
    private static Team newTeam() {
        Scoreboard BOARD = Bukkit.getScoreboardManager().getMainScoreboard();
        Team TEAM = BOARD.getTeam(TagInteractor.TEAM_NAME);
        if(TEAM == null) TEAM = BOARD.registerNewTeam(TagInteractor.TEAM_NAME);
        TEAM.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        TEAM.setCanSeeFriendlyInvisibles(false);
        return TEAM;
    }
}
