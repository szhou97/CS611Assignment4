
public class Messages {
    // General Game Message
    public static final String TITLE = ColorScheme.ANSI_PURPLE
        + "\n\n\n\n\n                              ******************************\n"
        + "                              *      HEROS AND LEGENDS     *\n"
        + "                              ******************************\n\n\n\n\n"
        + ColorScheme.ANSI_RESET;
    public static final String WELCOME_MESSAGE = "\n\n\n              "
        + "Welcome to Heros and Legends, and get ready for the adventure!\n\n\n";
    
    public static final String WELCOME_PROMPT = "\n\n\n\n              "
        + "Type: 'start' to start the game, 'help' for basic instructions\n";
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
        + "                              #           Market           #\n"
        + "                              ==============================\n\n\n"
        + ColorScheme.ANSI_RESET;
    public static final String MARKET_WELCOME = "                                  "
                                        +"Welcome to the Market\n\n\n";
    public static final String HERO_SELECTION = "\n\nSelect the hero" 
                                        + "\nSelect 0 to exit market";
    public static final String MARKET_SELECTION = "\n\nSelect to 1: buy, 2: sell, 3: view hero inventory" 
                                        + "\nSelect 0 to go back to previous menu";
    public static final String INVENTORY_TYPE = "\n\nSelect type of item, "
                                        + "\nSelect 0 to go back to previous menu";
    public static final String INVENTORY_SUBTYPE = "\n\nSelect subtype of the select item; "
                                        + "\nSelect 0 to go back to previous menu";
    public static final String ITEM = "\n\nSelect an item;"
                                        + "\nSelect 0 to go back to previous menu";;

    // Battle Message
    public static final String MONSTER_ENCOUNTER = ColorScheme.ANSI_PURPLE
                                        + "\n\n\n\n                              " 
                                        + "Some monsters have appeared!!"
                                        + "                              \n\n\n\n"
                                        + ColorScheme.ANSI_RESET;
    public static final String MOVE_SELECTION = "Select:"
        +ColorScheme.ANSI_GREEN + "\n\t'equip'" + ColorScheme.ANSI_RESET + " to equip weapon/armory"
        +ColorScheme.ANSI_GREEN + "\n\t'attack'" + ColorScheme.ANSI_RESET + " to attack the monster"
        +ColorScheme.ANSI_GREEN + "\n\t'cast'" + ColorScheme.ANSI_RESET + " to cast a spell"
        +ColorScheme.ANSI_GREEN + "\n\t'use'" + ColorScheme.ANSI_RESET + " to use a potion";
    public static final String[] MOVE_OPTIONS = {
        "attack", "equip", "cast", "use"
    };
    // 
    
}
