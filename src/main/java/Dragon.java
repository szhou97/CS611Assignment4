public class Dragon extends Monster {
    private final String monsterType;
    public Dragon(String name, int level, int health, 
            int baseDamage, int baseDefense, int dodgeChance) {
        super(name, level, health, baseDamage, baseDefense, dodgeChance);
        this.monsterType = "Dragon";
    }

    @Override
    String getType() {
        return this.monsterType;
    }

    
    
}
