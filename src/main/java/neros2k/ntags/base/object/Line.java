package neros2k.ntags.base.object;
import org.bukkit.entity.Player;
public class Line {
    private final Player PLAYER;
    private final String CONTENT;
    public Line(Player PLAYER, String CONTENT) {
        this.PLAYER = PLAYER;
        this.CONTENT = CONTENT;
    }
    public Player getPlayer() {
        return this.PLAYER;
    }
    public String getContent() {
        return this.CONTENT;
    }
}
