public class Spell extends Item {
    public Spell(String[] categories, String type, String name, int price, int minLevel, 
                    int damage, int manaCost) {
        super(categories, type, name, price, minLevel);
        this.attributes.add("damage", damage);
        this.attributes.add("mana_cost", manaCost);
    }

    public int getManaCost() {
        return this.attributes.get("mana_cost");
    }

    public int getDamage() {
        return this.attributes.get("damage");
    }

    public int reduceAttribute(int baseAttribute) {
        return (int) (baseAttribute * 0.9);
    }
    
}
