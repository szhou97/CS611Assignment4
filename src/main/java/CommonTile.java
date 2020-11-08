public class CommonTile extends ReachableTile {
    public CommonTile() {
        this.type = " ";
    }

    @Override
    boolean reachable() {
        return true;
    }
}
