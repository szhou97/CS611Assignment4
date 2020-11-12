public class Market extends ReachableTile {
    private Inventory stock;
    public Market(Inventory stock) {
        this.type = ColorScheme.ANSI_GREEN + "M" + ColorScheme.ANSI_RESET;
        this.stock = stock;
    }

    private int menuSelection(int min, int max, String message) {
        System.out.println(message);
        return Controller.intSelection(min, max);   
    }

    private void playerBuy(Hero hero, Item item) {
        int balance = hero.getAttribute("wealth") - item.getAttribute("price");
        if (balance < 0) {
            System.out.println(ColorScheme.ANSI_RED
                + "Insufficient funds"
                + ColorScheme.ANSI_RESET);
            return;
        } else {
            System.out.println("\n\tCurrent balance: $" + hero.getAttribute("wealth"));
            System.out.println("\n\tItem price: $" + item.getAttribute("price"));
            System.out.println("\n\tConfirm purcase?");
            int selection = this.menuSelection(1, 2, Messages.YES_OR_NO);
            if (selection == 1) {
                hero.buy(item);
                System.out.println("\n\tPURCHASE SUCCESSFUL. Current balance: $"
                    + hero.getAttribute("wealth"));
            } else {
                System.out.println("\n\tPURCHASE CANCELED");
            }
        }
    }

    private void playerSell(Hero hero, Item item) {
        System.out.println("\n\tCurrent balance: $" + hero.getAttribute("wealth"));
        System.out.println("\n\tItem price: $" + item.getAttribute("price")/2);
        System.out.println("\n\tConfirm sell?");
        int selection = this.menuSelection(1, 2, Messages.YES_OR_NO);
        if (selection == 1) {
            hero.sell(item);
            System.out.println("\n\tSALE SUCCESSFUL. Current balance: $" 
                + hero.getAttribute("wealth"));
        } else {
            System.out.println("\n\tSALE CANCELLED");
        }
    }

    private void prompt(Hero hero) {
        boolean valid = true;
        Item item = null;
        while (valid) {
            int selection = this.menuSelection(0, 3, Messages.MARKET_SELECTION);
            if (selection == 0) {
                break;
            } else if (selection == 1) {
                Query iq = new Query();
                item = (Item) iq.processItemRequest(stock.getTypes(), stock);
                if (item != null) {
                    if (item.getMinLevel() > hero.getAttribute("level")) {
                        System.out.println("Minimum level required not met. "
                                + "Level up before purchasing!");
                    } else {
                        this.playerBuy(hero, item);
                    }
                }
            } else if (selection == 2) {
                PlayerInventory pi = (PlayerInventory) hero.getInventory();
                Query iq = new Query();
                item = (Item) iq.processItemRequest(pi.getTypes(), pi);
                if (item != null) {
                    this.playerSell(hero, item);
                }
            } else if (selection == 3) {
                hero.getInventory().printInventory();
            }
        }
    }

    @Override
    public void arrive(Player player, boolean firstArrival) {
        this.player = player;
        this.playerExists = true;
        System.out.print("\n\n\n");
        System.out.println(Messages.MARKET);
        System.out.println(Messages.MARKET_WELCOME);
        if (firstArrival) {
            System.out.println("                         "+
                "Stock up before starting your adventure!!");
        }
        boolean complete = false;
        while (!complete) {
            Hero hero = player.selectHero();
            if (hero != null) {
                this.prompt(hero);
            } else {
                complete = true;
            }
        }
    }

    public void viewStock() {
        this.stock.printInventory();
    }


    @Override
    boolean reachable() {
        return true;
    }
}
