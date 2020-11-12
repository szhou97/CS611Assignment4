public class Potion extends Item {
    private String name;
    private String attribute;
    public Potion(String[] categories, String type, String name, int price, int minLevel, 
                        int attIncrease, String attribute) {
        super(categories, type, name, price, minLevel);
        this.attributes.add(attribute, attIncrease);
        this.name = name;
        this.attribute = attribute;
    }

    public String getAttributeAffected() {
        return this.attribute;
    }

    public int getAttributeIncrease() {
        return this.attributes.get(attribute);
    }
    
    @Override
    public String[] getAttributes() {
        String[] attributes = this.attributes.getAttributes();
        String[] result = new String[attributes.length + 2];
        result[0] = this.name;
        result[attributes.length + 1] = this.attribute;
        for (int i = 0; i < attributes.length; i++) {
            result[i + 1] = attributes[i];
        }
        return result;
    }
}
