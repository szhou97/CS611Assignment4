public abstract class Element {
    private final String type;
    private final String name;
    private final String[] categories;
    protected Attributes attributes;
    public Element(String[] categories, String type, String name) {
        this.type = type;
        this.name = name;
        this.attributes = new Attributes();
        this.categories = categories;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public int getAttribute(String key) {
        return this.attributes.get(key);
    }

    public void changeAttribute(String key, int newValue) {
        this.attributes.replace(key, newValue);
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }


    public String[] getAttributes() {
        String[] attributes = this.attributes.getAttributes();
        String[] result = new String[attributes.length + 1];
        result[0] = this.name;
        for (int i = 0; i < attributes.length; i++) {
            result[i + 1] = attributes[i];
        }
        return result;
    }

    public boolean equals(Element element) {
        return this.name.equals(element.name) && this.type.equals(element.type);
    }
}
