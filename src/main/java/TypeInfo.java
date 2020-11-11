public class TypeInfo {
    public final static String HERO_FORMAT = 
        "%-20s%-5s%-9s%-8s%-10s%-7s%-10s\n";
    
    public final static String MONSTER_FORMAT = 
        "%-15s%-6s%-7s%-9s%-20s\n";
    
    public final static String WEAPON_FORMAT = 
        "%-8s%-6s%-7s%-8s%-16s\n";

    public static final String ARMOR_FORMAT = 
        "%-16s%-5s%-15s%-16s\n";

    public static final String POTION_FORMAT = 
        "%-16s%-5s%-17s%-20s%-20s\n";

    public static final String SPELL_FORMAT = 
        "%-20s%-5s%-16s%-7s%-10s\n";

    public final static String ITEM_BRACKET = 
        ColorScheme.ANSI_GREEN 
        + "*********************************************" 
        + "********************************************"
        + ColorScheme.ANSI_RESET;    

    public final static String HERO_BRACKET = 
        ColorScheme.ANSI_BLUE 
        + "*********************************************" 
        + "********************************************"
        + ColorScheme.ANSI_RESET;

    public final static String MONSTER_BRACKET = 
        ColorScheme.ANSI_BLUE 
        + "*********************************************" 
        + "********************************************"
        + ColorScheme.ANSI_RESET;

    
    public final static String GRID_INFO = "\n\t"
        + ColorScheme.ANSI_RED
        + "X"
        + ColorScheme.ANSI_RESET
        + ": Current location    "
        + ColorScheme.ANSI_GREEN
        + "M"
        + ColorScheme.ANSI_RESET
        + ": Market    "
        + "&: Unreachable space";
        
    public static String getFormat(String type) {
        String format = "";
        switch (type) {
            case "weaponry":
                format = WEAPON_FORMAT;
                break;
            case "armory":
                format = ARMOR_FORMAT;
                break;
            case "potions":
                format = POTION_FORMAT;
                break;
            case "icespells":
                format = SPELL_FORMAT;
                break;
            case "firespells":
                format = SPELL_FORMAT;
                break;
            case "lightningspells":
                format = SPELL_FORMAT;
                break;
            case "warriors":
                format = HERO_FORMAT;
                break;
            case "sorcerers":
                format = HERO_FORMAT;
                break;
            case "paladins":
                format = HERO_FORMAT;
                break;
            case "hero":
                format = HERO_FORMAT;
                break;
            case "dragons":
                format = MONSTER_FORMAT;
                break;
            case "exoskeletons":
                format = MONSTER_FORMAT;
                break;
            case "spirits":
                format = MONSTER_FORMAT;
                break;
            case "monster":
                format = MONSTER_FORMAT;
                break;
        }
        return format;
    }
}
