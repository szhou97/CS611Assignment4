public abstract class Monster extends Character {
    protected int baseDamage, baseDefense, dodgeChance;
    public Monster(String name, int level, int health, 
                    int baseDamage, int baseDefense, int dodgeChance) {
        super(name, level, health);
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
        this.dodgeChance = dodgeChance;
    }
    
}
