package n2k_.ntags.base;
import n2k_.ntags.base.model.ConfigModel;
import n2k_.ntags.base.object.State;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public interface IInteractor {
    void init();
    void sendStateAB(Player PLAYER, Player CLICKED_PLAYER);
    void loadPlayer(Player PLAYER);
    void hidePlayerTag(String NAME, boolean IN_DATA, boolean IN_GAME);
    void showPlayerTag(String NAME, boolean IN_DATA, boolean IN_GAME);
    State getState(Player PLAYER);
    JavaPlugin getPlugin();
    ConfigModel getConfig();
}
