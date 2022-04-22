package neros2k.ntags.base.object;
public record State(String NAME, boolean HIDE) {
    public String getName() {
        return this.NAME;
    }
    public boolean isHide() {
        return this.HIDE;
    }
}
