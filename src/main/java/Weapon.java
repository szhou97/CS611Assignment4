public class Weapon extends Item {
    public Weapon(String type, String name, int price, int minLevel, int damage, int hand) {
        super(type, name, price, minLevel);
        this.attributes.add("damage", damage);
        this.attributes.add("hand", hand);

    }

    public int getBaseDamage() {
        return this.attributes.get("damage");
    }

    public int getNumberHands() {
        return this.attributes.get("hand");
    }
    
}
