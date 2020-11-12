import java.util.ArrayList;

public class Query {
    public Query() {

    }

    

    private int menuSelection(int min, int max, String message) {
        System.out.println(message);
        return Controller.intSelection(min, max);   
    }

    public Element getElement(ArrayList<Element> list) {

        if (list.size() == 0) return null;
        Element element = null;
        int selection = menuSelection(0, list.size(), Messages.ITEM);
        if (selection == 0) {
            element = null;
        } else {
            element = list.get(selection - 1);
        }

        return element;
        
    }

    public Element getItemList(ElementCollection collection) {
        if (collection.isEmpty()) {
            System.out.println(ColorScheme.ANSI_RED 
                    + "EMPTY"
                    + ColorScheme.ANSI_RESET);
            return null;
        }
        boolean valid = true;
        boolean multi = true;
        Element element = null;
        while (valid) {
            ArrayList<String> types = collection.getTypes();
            int selection;
            if (types.size() == 1) {
                selection = 1;
                multi = false;
            } else {
                collection.printTypes();
                selection = menuSelection(0, types.size(), Messages.INVENTORY_SUBTYPE);
            }
            if (selection == 0) {
                break;
            } else {
                String type = types.get(selection - 1);
                String format = TypeInfo.getFormat(type);
                collection.printElement(type, format);
                ArrayList<Element> list = collection.getElementList(type);
                element = this.getElement(list);
                if (element != null || !multi) {
                    valid = false;
                }
            }
        }
        return element;
    }

    private void printTypes(String[] types) {
        System.out.print("\n\t");
        int index = 1;
        for (String type : types) {
            System.out.print(ColorScheme.ANSI_GREEN);
            System.out.print(index + ": " + type + "\t");
            System.out.print(ColorScheme.ANSI_RESET);
            index++;
        }
    }

    public Element processItemRequest(String[] types, Inventory inventory) {
        Element element = null;
        int selection = -1;
        if (types.length == 1) {
            selection = 1;
        } else {
            this.printTypes(types);
            selection = menuSelection(0, 4, Messages.INVENTORY_TYPE);
        }
            
        if (selection == 0) {
            element = null;
        } else {
            String type = types[selection - 1];
            System.out.println("Choosed " + type);
            ElementCollection collection = inventory.getCollection(type);
            element = this.getItemList(collection);
        }
        return element;
    }
}
