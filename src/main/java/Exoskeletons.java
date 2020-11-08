public class Exoskeletons extends Monster {
    private final String monsterType;
    public Exoskeletons(String name, int level, int health, 
                int baseDamage, int baseDefense, int dodgeChance) {
        super(name, level, health, baseDamage, baseDefense, dodgeChance);
        this.monsterType = "Exoskeletons";
    }

    @Override
    String getType() {
        return this.monsterType;
    }
}
