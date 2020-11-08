public class UnreachableTile extends Tile {
    public UnreachableTile() {
        this.type = "&";
        this.playerExists = false;
    }

    @Override
    boolean reachable() {
        return false;
    }
}
