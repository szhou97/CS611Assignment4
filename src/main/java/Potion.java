public class Potion extends Item {
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

    public boolean usage() {
        if (usage <= 0) 
            return false;
        else
            return true;
    }

    public void usePotion() {
        this.usage--;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public int getAttIncrease() {
        return this.attIncrease;
    }
    
}
