public class Monster extends Character {
    public Monster(String type, String name, int level, int health, 
                    int damage, int defense, int dodgeChance) {
        super(type, name, level, health);
        this.attributes.add("damage", damage);
        this.attributes.add("defense", defense);
        this.attributes.add("dodge_chance", dodgeChance);
    }

    public int getDamage() {
        return this.attributes.get("damage");
    }

}
