import java.util.ArrayList;
/**
 * Initialize the player of the game by letting the user choose heros.
 * This class is created and called at the beginning of the game
 */
public class PlayerInit {
    ElementCollection heros;                // The hero pool is a collection
                                            //  that consists of all the 
                                            //   available heros
    public PlayerInit(ElementCollection heros) {
        this.heros = heros;
    }

    public Player initPlayer() {
        Player player = new Player();
        this.selectHeros(player);
        return player;
    }

    /**
     * Select a hero in the hero pool
     */
    private Element selectHero(boolean enough) {
        Element hero = null;
        ArrayList<String> types = heros.getTypes();
        System.out.print("\nChoose: ");
        int index = 0;
        boolean selected = false;
        while (!selected) {
            for (String type : types) {
                System.out.print((index + 1) + ":" + type + ", ");
                index++;
            }
            int min = 1;
            if (enough) {
                System.out.println("\n\n\nSelect 0 to finish selecting hero.");
                min = 0;
            }
            System.out.println("");

            int selection = Controller.intSelection(min, types.size());
            if (selection == 0) return null;
            String selectedType = types.get(selection - 1);
            this.heros.printElement(selectedType, TypeInfo.HERO_FORMAT);
            ArrayList<Element> heroList = this.heros.getElementList(selectedType);
            System.out.println("\n\n\nPlease select a hero. "
                            + "To go back to previous menu, select 0");
            selection = Controller.intSelection(0, heroList.size());
            if ( selection != 0 ) {
                selected = true;
                hero = heroList.get(selection - 1);
            } else {
                index = 0;
            }
        }
        return hero;
        
    }

    public void selectHeros(Player player) {
        this.heros.printElements(TypeInfo.HERO_BRACKET);
        int count = 0;
        boolean enough = false;
        System.out.print(ColorScheme.ANSI_RED);
        System.out.println("\n\n\nYou can select up to three heros for this game.");
        System.out.println("\nPlease select the type of hero you would like to choose\n");
        System.out.print(ColorScheme.ANSI_RESET);
        while (count < 3) {
            Element hero = this.selectHero(enough);
            if (hero != null) {
                player.addHero(hero);
                player.setCategories(heros.getCategories());
            } else {
                break;
            }
            count++;
            if (count > 0) {
                enough = true;
            }
            System.out.println("\nSelected " + count + " hero.");
            player.printHeros();
        }
    }
}
