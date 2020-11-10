import java.lang.ProcessBuilder.Redirect.Type;
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

    public void printInventory() {
        for (String type : types) {
            String format = null;
            String bracket = TypeInfo.ITEM_BRACKET;
            switch (type) {
                case "weaponry":
                    format = TypeInfo.WEAPON_FORMAT;
                    break;
                case "armory":
                    format = TypeInfo.ARMOR_FORMAT;
                    break;
                case "potions":
                    format = TypeInfo.POTION_FORMAT;
                    break;
                case "spells":
                    format = TypeInfo.SPELL_FORMAT;
                    break;
            }
            this.inventory.get(type).printElements(format, bracket);
        }
    }
}