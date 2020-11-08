import java.util.Scanner;

public class Controller {
    private Grid grid;
    public Controller(Grid grid) {
        this.grid = grid;

    }

    public int changePositions() {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            String input = scan.next().toLowerCase().trim();
            if (input.equals("w") || input.equals("a") 
                || input.equals("s") || input.equals("d")) {
                
                this.grid.updatePosition(input);
                valid = true;
            } else if (input.equals("q")) {
                return -1;
            } else {
                System.out.println("Invalid input");
            }
        }
        return 0;
    }
}