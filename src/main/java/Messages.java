
public class Messages {
    // General Game Message
    public static final String TITLE = ColorScheme.ANSI_PURPLE
        + "\n\n\n\n\n                              ******************************\n"
        + "                              *      HEROS AND LEGENDS     *\n"
        + "                              ******************************\n\n\n\n\n"
        + ColorScheme.ANSI_RESET;
    public static final String WELCOME_MESSAGE = "\n\n\n              "
        + "Welcome to Heros and Legends, and get ready for the adventure!\n\n\n";
    
    public static final String WELCOME_PROMPT = "\n\n\n\n    "
        + "Type: 'start' to start the game, 'help' for basic instructions, 'quit' to quit\n";
    public static final String YES_OR_NO = "\n\t"
                    + ColorScheme.ANSI_GREEN 
                    + "1. YES" 
                    + ColorScheme.ANSI_RESET
                    + "\t"
                    + ColorScheme.ANSI_RED
                    + "2. NO"
                    + ColorScheme.ANSI_RESET;
    // Market messages
    public static final String MARKET = ColorScheme.ANSI_GREEN 
        + "\n\n\n                              ==============================\n"
        + "                              #           MARKET           #\n"
        + "                              ==============================\n\n\n"
        + ColorScheme.ANSI_RESET;
    
    public static final String BATTLEFIELD = ColorScheme.ANSI_PURPLE 
        + "\n\n\n                              ==============================\n"
        + "                              #           BATTLE           #\n"
        + "                              ==============================\n\n\n"
        + ColorScheme.ANSI_RESET;
    public static final String MARKET_WELCOME = "                                  "
                                        +"Welcome to the Market\n\n\n";
    public static final String MARKET_SELECTION = "\n\nSelect to 1: buy, 2: sell, 3: view hero inventory" ;
    public static final String INVENTORY_TYPE = "\n\nSelect type of item, ";
    public static final String INVENTORY_SUBTYPE = "\n\nSelect subtype of the select item; ";
    public static final String ITEM = "\n\nSelect an item;";

    // Battle Message
    public static final String MONSTER_ENCOUNTER = ColorScheme.ANSI_PURPLE
                                        + "\n\n\n\n                              " 
                                        + "Some monsters have appeared!!"
                                        + "                              \n\n\n\n"
                                        + ColorScheme.ANSI_RESET;
    public static final String[] MOVE_OPTIONS = {
        "a", "c", "i", "e", "u", "h", "q"
    };
    // 
    public static final String HELP = "When in map view, select: " 
    
        +ColorScheme.ANSI_GREEN + "\n\t'w'" + ColorScheme.ANSI_RESET + " to move up one cell"
        +ColorScheme.ANSI_GREEN + "\n\t'a'" + ColorScheme.ANSI_RESET + " to move left one cell"
        +ColorScheme.ANSI_GREEN + "\n\t's'" + ColorScheme.ANSI_RESET + " to move down one cell"
        +ColorScheme.ANSI_GREEN + "\n\t'd'" + ColorScheme.ANSI_RESET + " to move right one cell"
        +ColorScheme.ANSI_GREEN + "\n\t'q'" + ColorScheme.ANSI_RESET + " to quit the game\n\n"
        +"During battles, select: "
        +ColorScheme.ANSI_GREEN + "\n\t'a'" + ColorScheme.ANSI_RESET + " to attack the monster"
        +ColorScheme.ANSI_GREEN + "\n\t'c'" + ColorScheme.ANSI_RESET + " to cast a spell\n\n"
        +"Throughout the game (exclude menus), the following actions can be performed by select: "
        +ColorScheme.ANSI_GREEN + "\n\t'i'" + ColorScheme.ANSI_RESET + " to view current hero information"
        +ColorScheme.ANSI_GREEN + "\n\t'e'" + ColorScheme.ANSI_RESET + " to equip/unequip weapon/armory"
        +ColorScheme.ANSI_GREEN + "\n\t'u'" + ColorScheme.ANSI_RESET + " to use a potion\n\n"
        +ColorScheme.ANSI_GREEN + "\n\t'h'" + ColorScheme.ANSI_RESET + " to view this help message\n\n"
        +"When in menus, options will be listed. Selected 0 to traverse backwards: "
        +ColorScheme.ANSI_GREEN + "\n\t'0'" + ColorScheme.ANSI_RESET + " to return to higher menu\n\n"
            
        
        +"Good luck adventurers!!";

}
