public class Warrior extends Hero {
    private final String heroType;
    public Warrior(String name, int level, int health, 
            int strength, int agility, int wealth, int experience) {
        super(name, level, health, strength, agility, wealth, experience);
        this.heroType = "Warrior";

    }


    @Override
    String getType() {
        return this.heroType;
    }
    
}
