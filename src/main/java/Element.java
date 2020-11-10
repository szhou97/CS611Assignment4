public abstract class Element {
    private final String type;
    private final String name;
    protected Attributes attributes;
    public Element(String type, String name) {
        this.type = type;
        this.name = name;
        this.attributes = new Attributes();
    }

    public String getType() {
        return this.type;
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
}
