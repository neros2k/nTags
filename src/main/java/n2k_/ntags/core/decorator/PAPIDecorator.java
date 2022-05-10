package n2k_.ntags.core.decorator;
import me.clip.placeholderapi.PlaceholderAPI;
import n2k_.ntags.base.ADecorator;
import n2k_.ntags.base.object.Line;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public final class PAPIDecorator extends ADecorator {
    public PAPIDecorator(@NotNull Line OBJECT) {
        super(OBJECT);
    }
    @Override @NotNull
    public String getContent() {
        Player PLAYER = super.getPlayer();
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return PlaceholderAPI.setPlaceholders(PLAYER, super.getContent());
        } else return super.getContent();
    }
}
