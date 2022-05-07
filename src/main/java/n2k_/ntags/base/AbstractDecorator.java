package n2k_.ntags.base;
import n2k_.ntags.base.object.Line;
import org.jetbrains.annotations.NotNull;
public abstract class AbstractDecorator extends Line {
    public AbstractDecorator(@NotNull Line OBJECT) {
        super(OBJECT.getPlayer(), OBJECT.getContent());
    }
}
