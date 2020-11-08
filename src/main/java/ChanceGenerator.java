import java.util.Random;

public class ChanceGenerator {
    private ChanceGenerator() {

    }

    public static boolean generateChance(int percentage) {
        Random random = new Random();
        return random.nextInt(100) < percentage;
    }

    public static int generateRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }
}
