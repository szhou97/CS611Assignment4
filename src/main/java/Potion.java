public class Potion extends Item implements Comsumable{
    private final String attAffected;
    private final int attIncrease;
    private int usage;
    public Potion(String name, int price, int minLevel, 
                        int attIncrease, String attAffected) {
        super(name, price, minLevel);
        this.attAffected = attAffected;
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
    
}
