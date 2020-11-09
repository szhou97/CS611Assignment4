public class Weapon extends Item {
    public Weapon(String name, int price, int minLevel, int damage, int hand) {
        super(name, price, minLevel);
        this.attributes.add("damage", damage);
        this.attributes.add("hand", hand);
        this.attNames.add("damage");
        this.attNames.add("hand");

    }

    public int getBaseDamage() {
        return this.attributes.get("damage");
    }

    public int getNumberHands() {
        return this.attributes.get("hand");
    }
    
}
