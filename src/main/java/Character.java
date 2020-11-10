public abstract class Character extends Element {
    private final int level;
    private final int health;
    public Character(String type, String name, int level, int health) {
        super(type, name);
        this.attributes = new Attributes();
        this.level = level;
        this.health = health;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }
}