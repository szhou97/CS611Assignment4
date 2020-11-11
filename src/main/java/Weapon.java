public class Weapon extends Item {
    public Weapon(String[] categories, String type, String name, int price, int minLevel, int damage, int hand) {
        super(categories, type, name, price, minLevel);
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
