public class Potion extends Item implements Comsumable {
    public Potion(String name, int price, int minLevel, 
                        int attIncrease, String attribute) {
        super(name, price, minLevel);
        this.attNames.add(attribute);
        this.attributes.add(attribute, attIncrease);
    }

    @Override
    public void useItem() {
        this.availableNumbers--;
    }
    
}
