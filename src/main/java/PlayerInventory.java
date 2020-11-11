public class PlayerInventory extends Inventory {
    public PlayerInventory() {
        this.init();
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
