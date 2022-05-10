package n2k_.ntags;
import n2k_.ntags.base.IInteractor;
import n2k_.ntags.core.TagInteractor;
import n2k_.ntags.base.model.ConfigModel;
import neros2k.jcapi.JCApi;
import neros2k.jcapi.JSONConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Optional;
public final class nTags extends JavaPlugin {
    private final IInteractor INTERACTOR;
    private JSONConfig<ConfigModel> JSON_CONFIG;
    public nTags() {
        this.INTERACTOR = new TagInteractor(this);
    }
    @Override
    public void onEnable() {
        List.of("nTags v1.0 by n2k_",
                "GitHub: https://github.com/neros2k",
                "Discord: n2k_#9665").forEach(this.getLogger()::info);
        if(Bukkit.getPluginManager().isPluginEnabled("JSONConfigAPI")) {
            Optional<JSONConfig<ConfigModel>> JSON_CONFIG_OPT = JCApi.getNew(
                    this, ConfigModel.class, "config.json");
            if(JSON_CONFIG_OPT.isPresent()) {
                this.JSON_CONFIG = JSON_CONFIG_OPT.get();
                this.JSON_CONFIG.reload();
            } else return;
            this.INTERACTOR.init();
        }
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.getLogger().info("Hooked into PlaceholderAPI");
        }
    }
    @NotNull
    public JSONConfig<ConfigModel> getJsonConfig() {
        return this.JSON_CONFIG;
    }
    @NotNull
    public ConfigModel getConfigModel() {
        return this.JSON_CONFIG.getJson();
    }
}
