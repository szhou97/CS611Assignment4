import java.util.ArrayList;
import java.util.Hashtable;
/**
 * The attributes class consists of a hashtable that matches an integer value
 * with a String, as well as a list of attribute names (String) to keep track
 * of the String keys. It is used by any element that keeps some sort of att-
 * -ributes
 * @author Shawn Zhou
 */
public class Attributes {
    private Hashtable<String, Integer> attributes;
    private ArrayList<String> attNames;
    public Attributes() {
        this.attributes = new Hashtable<String, Integer>();
        this.attNames = new ArrayList<String>();
    }

    /**
     * Add a pair of key/value to the hashtable
     * @param key
     * @param value
     */
    public void add(String key, Integer value) {
        this.attributes.put(key, value);
        this.attNames.add(key);
    }

    /**
     * Get a certain value by a given key
     * @param key
     * @return
     */
    public int get(String key) {
        return this.attributes.get(key);
    }

    /**
     * Replace a value for a given key
     */
    public void replace(String key, Integer value) {
        this.attributes.replace(key, value);
    }

    /**
     * Get the list of keys for this Attributes class
     * @return
     */
    public String[] getAttributes() {
        String[] result = new String[this.attNames.size()];
        for (int i = 0; i < this.attNames.size(); i++) {
            String key = this.attNames.get(i);
            result[i] = Integer.toString(this.attributes.get(key));
        }
        return result;
    }
}
