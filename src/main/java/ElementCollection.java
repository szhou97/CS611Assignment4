import java.util.ArrayList;
import java.util.Hashtable;

public class ElementCollection {
    private Hashtable<String, ArrayList<Element>> elements;
    private ArrayList<String> types;
    private String[] categories;
    public ElementCollection() {
        this.types = new ArrayList<String>();
        this.elements = new Hashtable<String, ArrayList<Element>>();
    }

    public void addType(String type) {
        this.types.add(type);
        this.elements.put(type, new ArrayList<Element>());
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public void add(Element element) {
        for (String type : this.types) {
            if (element.getType().equals(type)) {
                elements.get(type).add((element));
            }
        }
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

    public void printElements(String format, String bracket) {
        System.out.println(bracket);
        int count = 1;
        for (String type : this.types) {
            System.out.println(count + ", " + 
                ColorScheme.ANSI_RED + type + ColorScheme.ANSI_RESET 
                + ":");
            ArrayList<Element> elementList = elements.get(type);
            Object[][] table = this.getTable(elementList);
            this.printTable(table, format);
            count++;
            System.out.print("\n");
        }

        System.out.println(bracket);
    }
}
