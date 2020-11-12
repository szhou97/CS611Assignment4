import java.util.Scanner;

public class Controller {
    private Grid grid;
    private Player player;
    public Controller(Player player, Grid grid) {
        this.grid = grid;
        this.player = player;
    }

    public static void pressEnter() {
        Scanner scanner = new Scanner(System.in);
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

    public void mainControll() {
        String [] controls = {"w", "a", "s", "d", "q", "h", "i", "help", "e", "u"};
        String input = Controller.stringSelection(controls);
        System.out.print(ColorScheme.ANSI_CYAN);
        Hero hero = null;
        switch(input) {
            case "q":
                System.out.println("\nGoodbye");
                System.exit(0);
                break;
            case "h":
                System.out.println("\nSELECTED TO USE A POTION");
                break;
            case "i":
                System.out.println("\nSELECTED TO VIEW A HERO INVENTORY");
                player.viewHeroInventory();
                break;
            case "e":
                System.out.println("\nEQUIP/UNEQUIP AN ITEM FOR A HERO");
                hero = player.selectHero();
                if (hero != null) {
                    hero.equipAnItem();
                }
                break;
            case "u":
                System.out.println("\nUSE A POTION FOR A HERO");
                hero = player.selectHero();
                if (hero != null) {
                    hero.usePotion();
                }
                player.selectHero().usePotion();
                break;
            default:
                System.out.println("\nPRESS ENTER TO CONTINUE");
                grid.updatePosition(input);
        }
        System.out.print(ColorScheme.ANSI_RESET);
    }
}