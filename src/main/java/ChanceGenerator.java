import java.util.Random;
/**
 * A random number generator class. The static methods can be called anytime
 * when there is such need.
 */
public class ChanceGenerator {
    private ChanceGenerator() {

    }
    /**
     * @param percentage
     * @return true 'percentage' of times
     */
    public static boolean generateChance(int percentage) {
        Random random = new Random();
        return random.nextInt(100) < percentage;
    }

    /**
     * @param range
     * @return a random number in the given range
     */
    public static int generateRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }
}
