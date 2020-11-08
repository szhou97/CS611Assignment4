public class Dragon extends Monster {
    private final String monsterType;
    public Dragon(String name, int level, int health, 
            int damage, int defense, int dodgeChance) {
        super(name, level, health, damage, defense, dodgeChance);
        this.monsterType = "Dragon";
    }

    @Override
    String getType() {
        return this.monsterType;
    }
}
