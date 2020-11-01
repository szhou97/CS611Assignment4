public abstract class Character {
    protected final String name;
    protected int level, health;
    public Character(String name, int level, int health) {
        this.name = name;
        this.level = level;
        this.health = health;
    }

    abstract String getType();
    
    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }
}
