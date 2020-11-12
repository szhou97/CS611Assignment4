import java.util.Scanner;
/**
 * This class controls the player's movement on the grid, as well as some other
 * input methods
 */
public class Controller {
    private Grid grid;
    private Player player;
    public Controller(Player player, Grid grid) {
        this.grid = grid;
        this.player = player;
    }

    public static void pressEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PRESS ENTER TO CONTINUE");
        scanner.nextLine();
    }

    public static int intSelection(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int result = -1;
        while(!valid) {
            try {
                String input = scanner.next();
                int selection = Integer.parseInt(input.trim());
                if (selection < min || selection > max) {
                    throw new NumberFormatException();
                } else {
                    result = selection;
                    valid = true;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid input");
            }
        }
        return result;
    }

    public static String stringSelection(String[] options) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String result = "";
        
        while (!valid) {
            String input = scanner.next().toLowerCase().trim();
            for (String option : options) {
                if (option.equals(input)) {
                    result = option;
                    valid = true;
                    break;
                }
            }
            if (valid == false) {
                System.out.println("Unrecognized input, please try again");
            }
        }
        return result;
    }

    public int mainControll() {
        String [] controls = {"w", "a", "s", "d", "q", "h", "i", "h", "e", "u"};
        String input = Controller.stringSelection(controls);
        System.out.print(ColorScheme.ANSI_CYAN);
        Hero hero = null;
        switch(input) {
            case "q":
                player.printHeros();
                System.out.println("\nGOODBYE ADVENTURER");
                System.exit(0);
                break;
            case "h":
                System.out.println(Messages.HELP);
                break;
            case "i":
                System.out.println("\nSELECTED TO VIEW A HERO INFORMATION/INVENTORY");
                player.viewHeroInventory();
                Controller.pressEnter();
                break;
            case "e":
                System.out.println("\nSELECTED TO EQUIP/UNEQUIP AN ITEM FOR A HERO");
                hero = player.selectHero();
                if (hero != null) {
                    hero.equipAnItem();
                }
                break;
            case "u":
                System.out.println("\nSELECTED TO USE A POTION FOR A HERO");
                hero = player.selectHero();
                if (hero != null) {
                    hero.usePotion();
                }
                break;
            default:
                grid.updatePosition(input);
        }
        System.out.print(ColorScheme.ANSI_RESET);
        if (input.equals("q")) {
            return -1;
        } else {
            return 0;
        }
    }
}