public class Armor extends Item {
    public Armor(String type, String name, int price, int minLevel, int damageRedux) {
        super(type, name, price, minLevel);
        this.attributes.add("damage_reduction", damageRedux);
    }

    public int getDamageRedux() {
        return this.attributes.get("damage_reduction");
    }
}
