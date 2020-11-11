import java.util.Hashtable;

public abstract class Inventory {
    protected final String[] types = {
        "weaponry", "armory", "potions", "spells"
    };
    protected Hashtable<String, ElementCollection> inventory;
    public Inventory() {
        this.inventory = new Hashtable<String, ElementCollection>();
    }

    protected void init() {
        for (String type : this.types) {
            this.inventory.put(type, new ElementCollection());
        }
    }

    public ElementCollection getCollection(String type) {
        return this.inventory.get(type);
    }

    public String[] getTypes() {
        return this.types;
    }

    public void printTypes() {
        System.out.print("\n\t");
        int index = 1;
        for (String type : types) {
            System.out.print(ColorScheme.ANSI_GREEN);
            System.out.print(index + ": " + type + "\t");
            System.out.print(ColorScheme.ANSI_RESET);
            index++;
        }
    }

    public boolean isEmpty(String type) {
        return this.inventory.get(type).isEmpty();
    }

    public void printInventory() {
        for (String type : types) {
            String bracket = TypeInfo.ITEM_BRACKET;
            this.inventory.get(type).printElements(bracket);
        }
    }
}