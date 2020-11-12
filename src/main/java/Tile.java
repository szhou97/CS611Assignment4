/**
 * A tile class represents a tile in the grid.
 */
public abstract class Tile {
    protected String type;
    protected boolean playerExists;
    private final String player;
    public Tile() {
        this.type = " ";
        this.player = ColorScheme.ANSI_RED + "X" + ColorScheme.ANSI_RESET;
    }

    abstract boolean reachable();

    public String toString() {
        if (playerExists) {
            return "  " + this.player + "  ";
        } else {
            return "  " + this.type + "  ";
        }
    }
}
