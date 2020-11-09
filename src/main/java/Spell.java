public class Spell extends Item {
    private final String spellType;
    public Spell(String name, int price, int minLevel, 
                    int damage, int manaCost, String spellType) {
        super(name, price, minLevel);
        this.attributes.add("damage", damage);
        this.attributes.add("mana_cost", manaCost);
        this.spellType = spellType;
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
