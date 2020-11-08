public class Market extends ReachableTile {
    private Inventory stock;
    public Market() {
        this.type = ColorScheme.ANSI_GREEN + "M" + ColorScheme.ANSI_RESET;
        this.stock = new Inventory();
        this.initStock();
    }

    private void initStock() {

    }

    public void viewStock() {
        this.stock.printInventory();
    }

    public Item purchase(int itemNumber) {
        return this.stock.get(itemNumber);
    }

    @Override
    boolean reachable() {
        return true;
    }
}
