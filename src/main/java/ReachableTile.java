/**
 * A reachable tile can be accessed by a hero. Including methods that have to be
 * implemented by subclasses. Namely Commontiles and Markets
 */
public abstract class ReachableTile extends Tile {
    protected Player player;
    public ReachableTile() {
        this.playerExists = false;
        this.player = null;
    }

    
    public abstract void arrive(Player player, boolean firstArrival);
    public abstract void leave();
}
