import java.util.ArrayList;
import java.util.Hashtable;
/**
 * This class uses the data structure of a hashtable, where each key is matched
 * with an ArrayList. Each key is a type, with a list of elements with the same
 * type
 */
public class ElementCollection {
    private Hashtable<String, ArrayList<Element>> elements;
    private ArrayList<String> types;
    private String[] categories;
    public ElementCollection() {
        this.types = new ArrayList<String>();
        this.elements = new Hashtable<String, ArrayList<Element>>();
    }

    private void addType(String type) {
        this.types.add(type);
        this.elements.put(type, new ArrayList<Element>());
    }

    public ArrayList<Element> getAllElements() {
        ArrayList<Element> list = new ArrayList<Element>();
        for (String type : types) {
            for (Element element : this.getElementList(type)) {
                list.add(element);
            }
        }
        return list;
    }

    public ArrayList<String> getTypes() {
        return this.types;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getCategories() {
        return this.categories;
    }

    private void addElement(String elementType, Element element) {
        boolean exists = false;
        for (String type : this.types) {
            if (elementType.equals(type)) {
                exists = true;
                elements.get(type).add((element));
            }
        }
        if (!exists) {
            this.addType(elementType);
            this.elements.get(elementType).add(element);
            this.categories = element.getCategories();
        }
    }

    public void add(String elementType, Element element) {
        this.addElement(elementType, element);
        
    }
 
    public void add(Element element) {
        this.add(element.getType(), element);
       
    }

    public void remove(Element element) {
        for (String type : this.types) {
            ArrayList<Element> list = elements.get(type);
            for (Element item : list) {
                if (item.getName().equals(element.getName())) {
                    list.remove(item);
                    break;
                }
            }
        }
    }

    public int getTotalNumElements() {
        int result = 0;
        for(String type : types) {
            result += elements.get(type).size();
        }
        return result;
    }

    public void printTypes()  {
        System.out.print("\n\t");
        int index = 1;
        for (String type : types) {
            System.out.print(ColorScheme.ANSI_GREEN);
            System.out.print(index + ": " + type + "\t");
            System.out.print(ColorScheme.ANSI_RESET);
            index++;
        }
    }

    public ArrayList<Element> getElementList(String type) {
        return this.elements.get(type);
    }

    public Object[][] getTable(ArrayList<Element> elements) {
        final Object [][] table = new String[elements.size() + 1][];
        table[0] = this.categories;
        for (int i = 0; i < elements.size(); i++) {
            table[i + 1] = elements.get(i).getAttributes();
        }
        return table;
    }

    public void printTable(Object[][] table, String format) {
        int count = 0;
        for (final Object[] row : table) {
            if (count != 0) 
                System.out.print(" " + count + ",\t");
            else
                System.out.print("  \t"); 
                System.out.format(format, row);
            count++;
        }
        System.out.print("\n");
    }

    public void printElement(String type, String format) {
        System.out.println( ColorScheme.ANSI_RED + type 
            + ColorScheme.ANSI_RESET 
            + ":");
        ArrayList<Element> elementList = elements.get(type);
        Object[][] table = this.getTable(elementList);
        this.printTable(table, format);
        System.out.print("\n");
    }

    public void printElements(String bracket) {
        System.out.println(bracket);
        if (this.isEmpty()) {
            System.out.println(ColorScheme.ANSI_RED
                + "EMPTY"
                + ColorScheme.ANSI_RESET);
        } else {
            for (String type : this.types) {
                System.out.println(
                    "                              "
                    + "            " + type
                );
                if (this.elements.get(type).size() != 0) 
                    this.printElement(type, TypeInfo.getFormat(type));
                else {
                    System.out.println("\t\nEMPTY");
                }
            }
        }
        System.out.println(bracket);
    }

    public boolean exists(Element element) {
        boolean result = false;
        for (String type : types) {
            if (element.getType().equals(type)) {
                for (Element e : this.getElementList(type)) {
                    if (e.equals(element)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    private int numItems(String type) {
        return this.elements.get(type).size();
    }

    public boolean isEmpty() {
        int result = 0;
        for (String type : types) {
            result += this.numItems(type);
        }
        return result == 0;
    }
}
