public class Potion extends Item implements Comsumable{
    private final String attribute;
    private final int attIncrease;
    private int usage;
    public Potion(String name, int price, int minLevel, 
                        int attIncrease, String attribute) {
        super(name, price, minLevel);
        this.attribute = attribute;
        this.attIncrease = attIncrease;
        usage = 1;
    }

    @Override
    public boolean usage() {
        if (usage <= 0) 
            return false;
        else
            return true;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public int getAttIncrease() {
        return this.attIncrease;
    }
    
}
