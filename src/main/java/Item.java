import java.util.ArrayList;

public abstract class Item {
    private final String name;
    private final int price;
    private final int minLevel;
    protected final ArrayList<String> attNames;
    protected final Attributes attributes;
    public Item(String name, int price, int minLevel) {
        this.name = name;
        this.price = price;
        this.minLevel = minLevel;
        this.attributes = new Attributes();
        this.attNames = new ArrayList<String>();
    }
    
	public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getMinLevel() {
        return this.minLevel;
    }

    public String toString() {
        return null;
    }
}
