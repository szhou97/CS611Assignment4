import java.util.Scanner;

public class Controller {
    private Grid grid;
    public Controller(Grid grid) {
        this.grid = grid;
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
        String [] controls = {"w", "a", "s", "d", "q"};
        String input = Controller.stringSelection(controls);
        if (input.equals("q")) {
            System.exit(0);
        } else {
            grid.updatePosition(input);
        }
    }

    public int battleControl() {
        return 0;
    }
}