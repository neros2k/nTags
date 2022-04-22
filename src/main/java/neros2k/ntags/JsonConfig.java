package neros2k.ntags;
import com.google.gson.Gson;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public final class JsonConfig<T> {
    private final static Gson GSON = new Gson();
    private final Class<T> CLASS;
    private final Plugin PLUGIN;
    private final Path PATH;
    private T JSON;
    public JsonConfig(@NotNull Plugin PLUGIN, Class<T> CLASS, String NAME) {
        this.CLASS = CLASS;
        this.PLUGIN = PLUGIN;
        this.PATH = PLUGIN.getDataFolder().toPath().resolve(NAME);
    }
    public boolean reload() {
        try {
            this.JSON = GSON.fromJson(Files.newBufferedReader(this.PATH), this.CLASS);
            return true;
        } catch(IOException EXCEPTION) {
            this.createFile();
            if(!this.reload()) this.PLUGIN.getLogger().warning(EXCEPTION.toString());
            return false;
        }
    }
    public T getJson() {
        assert this.JSON != null;
        return this.JSON;
    }
    private void createFile() {
        if(!new File(this.PLUGIN.getDataFolder(), this.PATH.getFileName().toString()).exists()) {
            this.PLUGIN.getLogger().info("Creating: "+this.PATH.getFileName());
            this.PLUGIN.saveResource(this.PATH.getFileName().toString(), false);
        }
    }
}
