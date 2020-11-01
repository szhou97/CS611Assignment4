public class Spirit extends Monster {
    private final String monsterType;
    public Spirit(String name, int level, int health, 
                int baseDamage, int baseDefense, int dodgeChance) {
        super(name, level, health, baseDamage, baseDefense, dodgeChance);
        this.monsterType = "Spirit";
    }

    @Override
    String getType() {
        return this.monsterType;
    }
    
}
