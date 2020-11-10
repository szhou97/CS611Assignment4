public class Market extends ReachableTile {
    private Inventory stock;
    public Market(Inventory stock) {
        this.type = ColorScheme.ANSI_GREEN + "M" + ColorScheme.ANSI_RESET;
        this.stock = stock;
    }

    @Override
    public void arrive(Player player) {
        this.player = player;
        this.playerExists = true;
        System.out.println(Messages.market);
        System.out.println(Messages.marketWelcome);
    }

    public void viewStock() {
        this.stock.printInventory();
    }


    @Override
    boolean reachable() {
        return true;
    }
}
