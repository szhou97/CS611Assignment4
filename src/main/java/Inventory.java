import java.util.ArrayList;

public class Inventory {
    protected ArrayList<Item> weapons;
    protected ArrayList<Item> armors;
    protected ArrayList<Item> potions;
    protected ArrayList<Item> spells;
    public Inventory() {
        this.weapons = new ArrayList<Item>();
        this.armors = new ArrayList<Item>();
        this.potions = new ArrayList<Item>();
        this.spells = new ArrayList<Item>();
    }

    public void printInventory() {

    }

    public Item get(int index, int itemNumber) {
        return null;
    }

    public void addWeapon(Item weapon) {
        this.weapons.add(weapon);
    }

    public void addArmor(Item armor) {
        this.armors.add(armor);
    }

    public void addPotion(Item potion) {
        this.potions.add(potion);
    }

    public void addSpell(Item spell) {
        this.spells.add(spell);
    }
}