package neros2k.ntags.core;
import neros2k.ntags.base.AbstractDecorator;
import neros2k.ntags.base.InteractorInterface;
import neros2k.ntags.base.AbstractPresenter;
import neros2k.ntags.base.model.ConfigModel;
import neros2k.ntags.base.object.Line;
import neros2k.ntags.base.object.State;
import neros2k.ntags.core.decorator.PAPIDecorator;
import neros2k.ntags.core.decorator.NameDecorator;
import neros2k.ntags.core.presenter.CommandPresenter;
import neros2k.ntags.core.presenter.EventPresenter;
import neros2k.ntags.nTags;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
@SuppressWarnings("ALL")
public final class TagInteractor implements InteractorInterface<nTags> {
    private static final String TEAM_NAME = "n_hidden_tags";
    private final nTags PLUGIN;
    private final ArrayList<AbstractPresenter<?>> PRESENTER_LIST;
    private final TagsRepository TAGS_REPOSITORY;
    public TagInteractor(@NotNull nTags PLUGIN) {
        this.PLUGIN = PLUGIN;
        this.PRESENTER_LIST = new ArrayList<>();
        this.TAGS_REPOSITORY = new TagsRepository(this);
    }
    @Override
    public void init() {
        this.PRESENTER_LIST.add(new EventPresenter(this));
        if(this.PLUGIN.getConfigModel().ENABLE_CMDS) this.PRESENTER_LIST.add(new CommandPresenter(this));
        this.PRESENTER_LIST.forEach(AbstractPresenter::init);
        this.TAGS_REPOSITORY.init();
        Scoreboard BOARD = Bukkit.getScoreboardManager().getMainScoreboard();
        Team TEAM = BOARD.getTeam(TagInteractor.TEAM_NAME);
        if(TEAM == null) TEAM = BOARD.registerNewTeam(TagInteractor.TEAM_NAME);
        TEAM.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
    }
    public void sendStateAB(@NotNull Player PLAYER, Player CLICKED_PLAYER) {
        ConfigModel CONFIG_MODEL = this.getPlugin().getConfigModel();
        if(!CONFIG_MODEL.SEND_AB_FROM_DSPLD_PLYR && !this.getState(CLICKED_PLAYER).isHide()) return;
        AbstractDecorator OBJECT = new PAPIDecorator(new NameDecorator(new Line(CLICKED_PLAYER, CONFIG_MODEL.MESSAGES.AB_MSG)));
        PLAYER.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(OBJECT.getContent()));
    }
    public void loadPlayer(@NotNull Player PLAYER) {
        State STATE = this.getState(PLAYER);
        if(STATE.isHide()) {
            this.hidePlayerTag(PLAYER.getName(), false, true);
        } else {
            this.showPlayerTag(PLAYER.getName(), false, true);
        }
    }
    public void hidePlayerTag(@Nullable String NAME, boolean IN_DATA, boolean IN_GAME) {
        Scoreboard SCOREBOARD = Bukkit.getScoreboardManager().getMainScoreboard();
        if(IN_GAME) SCOREBOARD.getTeam(TagInteractor.TEAM_NAME).addEntry(NAME);
        if(IN_DATA) this.TAGS_REPOSITORY.setValue(new State(NAME, true));
    }
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
    public nTags getPlugin() {
        return this.PLUGIN;
    }
}
