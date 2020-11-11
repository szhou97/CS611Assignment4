import java.util.ArrayList;

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

    private Item getItem(ArrayList<Element> list) {

        if (list.size() == 0) return null;
        Item item = null;
        int selection = menuSelection(0, list.size(), Messages.marketSelection4);
        if (selection == 0) {
            item = null;
        } else {
            item = (Item) list.get(selection - 1);
        }

        return item;
        
    }

    private Item getItemList(ElementCollection collection) {
        if (collection.isEmpty()) {
            System.out.println(ColorScheme.ANSI_RED 
                    + "Inventory is empty"
                    + ColorScheme.ANSI_RESET);
            return null;
        }
        boolean valid = true;
        boolean multi = true;
        Item item = null;
        while (valid) {
            ArrayList<String> types = collection.getTypes();
            int selection;
            if (types.size() == 1) {
                selection = 1;
                multi = false;
            } else {
                collection.printTypes();
                selection = menuSelection(0, types.size(), Messages.marketSelection3);
            }
            if (selection == 0) {
                break;
            } else {
                String type = types.get(selection - 1);
                String format = TypeInfo.getFormat(type);
                collection.printElement(type, format);
                ArrayList<Element> list = collection.getElementList(type);
                item = this.getItem(list);
                if (item != null || !multi) {
                    valid = false;
                }
            }
        }
        return item;
    }

    private Item processItemRequest(Inventory sourceInventory) {
        boolean complete = false;
        Item item = null;
        while (!complete) {
            sourceInventory.printTypes();
            String[] types = sourceInventory.getTypes();
            int selection = menuSelection(0, 4, Messages.marketSelection2);
            if (selection == 0) {
                item = null;
                break;
            } else {
                String type = types[selection - 1];
                System.out.println("Choosed " + type);
                ElementCollection collection = sourceInventory.getCollection(type);
                item = this.getItemList(collection);
                if (item != null) {
                    complete = true;
                }
            }
        }
        return item;
    }

    private void playerBuy(Hero hero, Item item) {
        int balance = hero.getWealth() - item.getPrice();
        if (balance < 0) {
            System.out.println(ColorScheme.ANSI_RED
                + "Insufficient funds"
                + ColorScheme.ANSI_RESET);
            return;
        } else {
            System.out.println("\n\tCurrent balance: $" + hero.getWealth());
            System.out.println("\n\tItem price: $" + item.getPrice());
            System.out.println("\n\tConfirm purcase?");
            int selection = this.menuSelection(1, 2, Messages.YES_OR_NO);
            if (selection == 1) {
                hero.buy(item);
            }
        }
    }

    private void playerSell(Hero hero, Item item) {
        System.out.println("\n\tCurrent balance: $" + hero.getWealth());
        System.out.println("\n\tItem price: $" + item.getPrice());
        System.out.println("\n\tConfirm sell?");
        int selection = this.menuSelection(1, 2, Messages.YES_OR_NO);
        if (selection == 1) {
                hero.sell(item);
        }
    }

    private void prompt(Hero hero) {
        boolean valid = true;
        Item item = null;
        while (valid) {
            int selection = this.menuSelection(0, 3, Messages.marketSelection1);
            if (selection == 0) {
                break;
            } else if (selection == 1) {
                item = this.processItemRequest(stock);
                if (item != null) {
                    this.playerBuy(hero, item);
                }
            } else if (selection == 2) {
                item = this.processItemRequest(hero.getInventory());
                if (item != null) {
                    this.playerSell(hero, item);
                }
            } else if (selection == 3) {
                hero.getInventory().printInventory();
            }
        }
    }

    private void selectHero() {
        boolean valid = true;
        while (valid) {
            ArrayList<Hero> heros = player.getHeros();
            this.player.printWealth();
            int selection = menuSelection(0, heros.size(), Messages.MARKET_SELECTION);
            if (selection != 0) {
                this.prompt(heros.get(selection - 1));
            } else {
                valid = false;
            }
        }
    }

    @Override
    public void arrive(Player player, boolean firstArrival) {
        this.player = player;
        this.playerExists = true;
        System.out.print("\n\n\n");
        System.out.println(Messages.market);
        System.out.println(Messages.marketWelcome);
        this.selectHero();
    }

    public void viewStock() {
        this.stock.printInventory();
    }


    @Override
    boolean reachable() {
        return true;
    }
}
