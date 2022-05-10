package n2k_.ntags.core.decorator;
import n2k_.ntags.base.ADecorator;
import n2k_.ntags.base.object.Line;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public final class NameDecorator extends ADecorator {
    public NameDecorator(Line OBJECT) {
        super(OBJECT);
    }
    @Override @NotNull
    public String getContent() {
        Player PLAYER = super.getPlayer();
        return super.getContent().replaceAll("%name%", PLAYER.getName());
    }
}
