import java.util.ArrayList;

public abstract class Inventory {
    protected ArrayList<Item> weapons;
    protected ArrayList<Item> armors;
    protected ArrayList<Item> potions;
    protected ArrayList<Item> fireSpell;
    protected ArrayList<Item> iceSpell;
    protected ArrayList<Item> lightningSpell;
    public Inventory() {
        this.weapons = new ArrayList<Item>();
        this.armors = new ArrayList<Item>();
        this.potions = new ArrayList<Item>();
        this.fireSpell = new ArrayList<Item>();
        this.iceSpell = new ArrayList<Item>();
        this.lightningSpell = new ArrayList<Item>();
    }

    public void printInventory() {

    }

    public Item get(int index, int itemNumber) {
        return null;
    }
}