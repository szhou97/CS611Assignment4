import java.util.ArrayList;
import java.util.Hashtable;

public class Attributes {
    private Hashtable<String, Integer> attributes;
    private ArrayList<String> attNames;
    public Attributes() {
        this.attributes = new Hashtable<String, Integer>();
        this.attNames = new ArrayList<String>();
    }

    public void add(String key, Integer value) {
        this.attributes.put(key, value);
        this.attNames.add(key);
    }

    public int get(String key) {
        return this.attributes.get(key);
    }


    public String[] getAttributes() {
        String[] result = new String[this.attNames.size()];
        for (int i = 0; i < this.attNames.size(); i++) {
            String key = this.attNames.get(i);
            result[i] = Integer.toString(this.attributes.get(key));
        }
        return result;
    }
}
