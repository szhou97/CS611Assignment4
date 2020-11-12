public abstract class Item extends Element {
    protected int availableNumbers;
    public Item(String[] categories, String type, String name, int price, int minLevel) {
        super(categories, type, name);
        this.availableNumbers = -1;
        this.attributes.add("price", price);
        this.attributes.add("minimum_level", minLevel);
    }

    public void setAvailableNumbers(int number) {
        this.availableNumbers = number;
    }

    public int getMinLevel() {
        return this.attributes.get("minimum_level");
    }
}
