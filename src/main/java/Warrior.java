public class Warrior extends Hero {
    private final String heroType;
    public Warrior(String name, int level, int health, 
            int strength, int agility, int wealth, int experience) {
        super(name, level, health, strength, agility, wealth, experience);
        this.heroType = "Warrior";

    }

    @Override
    public void levelUp() {
        this.strength = (int) (this.strength * 1.05);
        this.agility = (int) (this.agility * 1.05);
    }

    @Override
    String getType() {
        return this.heroType;
    }
    
}
