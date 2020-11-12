/**
 * The player inventory adds the ability of add/remove item compared to the 
 * superclass inventory
 */
public class PlayerInventory extends Inventory {
    public PlayerInventory(String[] types) {
        super(types);
    }

    public void add(Element element) {
        for (String type : types) { 
            if (element.getType().contains(type)) {
                this.inventory.get(type).add(element);
            }
        }
    }

    public void remove(Element element) {
        for (String type : types) {
            if (element.getType().contains(type)) {
                this.inventory.get(type).remove(element);
            }
        }
    }
}
