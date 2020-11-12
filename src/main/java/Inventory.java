import java.util.Hashtable;
/**
 * The Inventory class contains a hashtable of collections. Each collecion
 * is a type of item available in this game. It contains some utility methods
 * for retrieving information of the inventory. The market stock and player
 * inventory both extends this class.
 */
public abstract class Inventory {
    protected Hashtable<String, ElementCollection> inventory;
    protected String[] types;
    public Inventory(String[] types) {
        this.inventory = new Hashtable<String, ElementCollection>();
        this.types = types;
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

    public boolean isEmpty() {
        boolean empty = true;
        for (String type : types) {
            empty = this.inventory.get(type).isEmpty();
            if (!empty) 
                break;
        }
        return empty;
    }

    public void printInventory() {
        
        for (String type : types) {
            String bracket = TypeInfo.ITEM_BRACKET;
            this.inventory.get(type).printElements(bracket);
        }
    }
}