package n2k_.ntags;
import n2k_.ntags.core.TagInteractor;
import n2k_.ntags.base.model.ConfigModel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.List;
public final class nTags extends JavaPlugin {
    private final TagInteractor INTERACTOR;
    private final JsonConfig<ConfigModel> JSON_CONFIG;
    public nTags() {
        this.JSON_CONFIG = new JsonConfig<>(this, ConfigModel.class, "config.json");
        this.INTERACTOR = new TagInteractor(this);
    }
    @Override
    public void onEnable() {
        this.JSON_CONFIG.reload();
        this.INTERACTOR.init();
        List.of("nTags v1.0 by neros2k",
                "GitHub: https://github.com/neros2k",
                "Discord: n2k#9665").forEach(this.getLogger()::info);
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.getLogger().info("Hooked into PlaceholderAPI");
        }
    }
    @NotNull
    public JsonConfig<?> getJsonConfig() {
        return this.JSON_CONFIG;
    }
    @NotNull
    public ConfigModel getConfigModel() {
        return this.JSON_CONFIG.getJson();
    }
}
