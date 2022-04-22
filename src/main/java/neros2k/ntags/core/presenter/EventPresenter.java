package neros2k.ntags.core.presenter;
import neros2k.ntags.base.AbstractPresenter;
import neros2k.ntags.base.model.ConfigModel;
import neros2k.ntags.core.TagInteractor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
public final class EventPresenter extends AbstractPresenter<TagInteractor> implements Listener {
    public EventPresenter(TagInteractor INTERACTOR) {
        super(INTERACTOR);
    }
    @Override
    public void init() {
        JavaPlugin PLUGIN = super.getInteractor().getPlugin();
        PLUGIN.getServer().getPluginManager().registerEvents(this, PLUGIN);
    }
    @EventHandler
    private void onPlayerJoin(@NotNull PlayerJoinEvent EVENT) {
        super.getInteractor().loadPlayer(EVENT.getPlayer());
    }
    @EventHandler
    private void onPlayerInteract(@NotNull PlayerInteractAtEntityEvent EVENT) {
        ConfigModel CONFIG_MODEL = this.getInteractor().getPlugin().getConfigModel();
        if(EVENT.isCancelled() || !CONFIG_MODEL.ENABLE_AB_MSG || !(EVENT.getRightClicked() instanceof Player)) return;
        super.getInteractor().sendStateAB(EVENT.getPlayer(), (Player) EVENT.getRightClicked());
    }
}

