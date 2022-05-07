package n2k_.ntags.core.presenter;
import n2k_.ntags.core.TagInteractor;
import n2k_.ntags.base.AbstractPresenter;
import n2k_.ntags.base.model.ConfigModel;
import n2k_.ntags.nTags;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.List;
public final class CommandPresenter extends AbstractPresenter implements CommandExecutor {
    public CommandPresenter(TagInteractor INTERACTOR) {
        super(INTERACTOR);
    }
    @Override
    public void init() {
        PluginCommand COMMAND = super.getInteractor().getPlugin().getCommand("tag");
        assert COMMAND != null;
        COMMAND.setExecutor(this);
        COMMAND.setAliases(List.of("ntags", "nt"));
    }
    @Override
    public boolean onCommand(@NotNull CommandSender SENDER, @NotNull Command COMMAND, @NotNull String STR, @NotNull String @NotNull [] ARGS) {
        ConfigModel CONFIG_MODEL = this.getInteractor().getConfig();
        if(ARGS.length == 0 || ARGS[0].equals("help")) {
            List.of(CONFIG_MODEL.MESSAGES.HELP_COMMAND).forEach(SENDER::sendMessage);
            return true;
        }
        if(ARGS[0].equals("reload")) {
            if(notEnoughPermission("ntags.command.reload", SENDER)) {
                SENDER.sendMessage(CONFIG_MODEL.MESSAGES.PERM_ERROR);
                return true;
            }
            ((nTags) this.getInteractor().getPlugin()).getJsonConfig().reload();
            SENDER.sendMessage(CONFIG_MODEL.MESSAGES.RELOAD_COMMAND);
            return true;
        }
        if(ARGS[0].equals("hide")) {
            if(notEnoughPermission("ntags.command.hide", SENDER)) {
                SENDER.sendMessage(CONFIG_MODEL.MESSAGES.PERM_ERROR);
                return true;
            }
            if(ARGS.length == 2) {
                Player PLAYER = this.getPlayerByName(ARGS[1]);
                if(PLAYER != null) {
                    this.getInteractor().hidePlayerTag(PLAYER.getName(), true, true);
                } else this.getInteractor().hidePlayerTag(ARGS[1], true, true);
                SENDER.sendMessage(CONFIG_MODEL.MESSAGES.HIDE_COMMAND
                        .replace("%player%", ARGS[1]));
                return true;
            }
            SENDER.sendMessage(CONFIG_MODEL.MESSAGES.HIDE_COMMAND_ERROR);
            return true;
        }
        if(ARGS[0].equals("show")) {
            if(notEnoughPermission("ntags.command.show", SENDER)) {
                SENDER.sendMessage(CONFIG_MODEL.MESSAGES.PERM_ERROR);
                return true;
            }
            if(ARGS.length == 2) {
                Player PLAYER = this.getPlayerByName(ARGS[1]);
                if(PLAYER != null) {
                    this.getInteractor().showPlayerTag(PLAYER.getName(), true, true);
                } else this.getInteractor().showPlayerTag(ARGS[1], true, true);
                SENDER.sendMessage(CONFIG_MODEL.MESSAGES.SHOW_COMMAND
                        .replace("%player%", ARGS[1]));
                return true;
            }
            SENDER.sendMessage(CONFIG_MODEL.MESSAGES.SHOW_COMMAND_ERROR);
            return true;
        }
        SENDER.sendMessage(CONFIG_MODEL.MESSAGES.UNKNOWN_COMMAND);
        return true;
    }
    private Player getPlayerByName(String NAME) {
        return this.getInteractor().getPlugin().getServer().getPlayer(NAME);
    }
    private boolean notEnoughPermission(String NAME, @NotNull CommandSender SENDER) {
        return !(SENDER.hasPermission("ntags.admin") && SENDER.hasPermission(NAME));
    }
}
