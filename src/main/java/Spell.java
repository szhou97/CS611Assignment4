public abstract class Spell extends Item implements Damage {
    private final int damage;
    private final int manaCost;
    public Spell(String name, int price, int minLevel, 
                            int damage, int manaCost) {
        super(name, price, minLevel);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public int getBaseDamage() {
        return this.damage;
    }
    
}
