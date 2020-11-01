public class Armor extends Item {
    private final int damageRedux;
    public Armor(String name, int price, int minLevel, int damageRedux) {
        super(name, price, minLevel);
        this.damageRedux = damageRedux;
    }
    
}
