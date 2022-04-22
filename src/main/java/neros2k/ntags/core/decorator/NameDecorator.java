package neros2k.ntags.core.decorator;
import neros2k.ntags.base.AbstractDecorator;
import neros2k.ntags.base.object.Line;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public final class NameDecorator extends AbstractDecorator {
    public NameDecorator(Line OBJECT) {
        super(OBJECT);
    }
    @Override @NotNull
    public String getContent() {
        Player PLAYER = super.getPlayer();
        return super.getContent().replaceAll("%name%", PLAYER.getName());
    }
}
