public abstract class Hero extends Character implements Playable {
    protected int strength, agility, wealth, experience;
    protected Inventory inventory;
    public Hero(String name, int level, int health, int strength, int agility,
                            int wealth, int experience) {
        super(name, level, health);
        this.inventory = new Inventory();
        this.strength = strength;
        this.agility = agility;
        this.wealth = wealth;
        this.experience = experience;
    }
}
