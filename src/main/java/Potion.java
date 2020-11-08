public class Potion extends Item implements Comsumable{
    private final String attribute;
    private final int attIncrease;
    public Potion(String name, int price, int minLevel, 
                        int attIncrease, String attribute) {
        super(name, price, minLevel);
        this.attribute = attribute;
        this.attIncrease = attIncrease;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public int getAttIncrease() {
        return this.attIncrease;
    }

    @Override
    public void useItem() {
        this.availableNumbers--;
    }
    
}
