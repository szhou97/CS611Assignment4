public abstract class Character extends Element {
    private int health;
    public Character(String[] categories, String type, String name, int level) {
        super(categories, type, name);
        this.attributes = new Attributes();
        this.attributes.add("level", level);
        this.health = level * 100;
        this.attributes.add("health", this.health);
    }
    public abstract int getDefense();
    public abstract void reduceDefense(int damage);
    public abstract int getDamage();
    public abstract void reset();

    public int getHealth() {
        return this.health;
    }
}