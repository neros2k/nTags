package neros2k.ntags.base;
import org.bukkit.plugin.java.JavaPlugin;
public interface InteractorInterface<T extends JavaPlugin> {
    void init();
    T getPlugin();
}
