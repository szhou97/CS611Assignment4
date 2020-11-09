import java.util.ArrayList;

public abstract class Item {
    private final String name;
    protected ArrayList<String> attNames;
    protected Attributes attributes;
    protected int availableNumbers;
    public Item(String name, int price, int minLevel) {
        this.name = name;
        this.availableNumbers = -1;
        this.attributes = new Attributes();
        this.attNames = new ArrayList<String>();
        this.attributes.add("price", price);
        this.attributes.add("minimum_level", minLevel);
        this.attNames.add("price");
        this.attNames.add("minimum_level");
    }

    public void setAvailableNumbers(int number) {
        this.availableNumbers = number;
    }
    
	public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.attributes.get("price");
    }

    public int getMinLevel() {
        return this.attributes.get("minimum_level");
    }

    public String toString() {
        return null;
    }
}
