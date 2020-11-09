public abstract class Character {
    private final String name;
    private final String type;
    protected Attributes attributes;
    public Character(String type, String name, int level, int health) {
        this.name = name;
        this.type = type;
        this.attributes = new Attributes();
        this.attributes.add("level", level);
        this.attributes.add("health", health);
    }

    public String getType() {
        return this.type;
    }
    
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
