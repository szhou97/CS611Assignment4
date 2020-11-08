public abstract class Character {
    private final String name;
    protected Attributes attributes;
    public Character(String name, int level, int health) {
        this.name = name;
        this.attributes = new Attributes();
        this.attributes.add("level", level);
        this.attributes.add("health", health);
    }

    abstract String getType();
    
    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.attributes.get("level");
    }

    public int getHealth() {
        return this.attributes.get("health");
    }
}
