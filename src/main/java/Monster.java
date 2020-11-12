public class Monster extends Character {
    private int defense;
    public Monster(String[] categories, String type, String name, int level, 
                    int damage, int defense, int dodgeChance) {
        super(categories, type, name, level);
        this.attributes.add("level", level);
        this.attributes.add("damage", damage);
        this.attributes.add("defense", defense);
        this.attributes.add("dodge_chance", dodgeChance);
        this.defense = defense;
    }

    @Override
    public int getDamage() {
        return this.attributes.get("damage");
    }

    @Override
    public int getDefense() {
        return this.attributes.get("defense");
    }

    @Override
    public void reduceDefense(int damage) {
        int newDefense = this.attributes.get("defense") - damage;
        this.attributes.replace("defense", newDefense);
    }
    @Override
    public void reset() {
        this.attributes.replace("defense", this.defense);
        this.attributes.replace("health", this.getHealth());
    }
}
