public class Armor extends Item implements Defense {
    private final int damageRedux;
    public Armor(String name, int price, int minLevel, int damageRedux) {
        super(name, price, minLevel);
        this.damageRedux = damageRedux;
    }

    public int getDamageRedux() {
        return this.damageRedux;
    }
    
}
