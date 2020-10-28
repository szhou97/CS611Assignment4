public class Main{
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.print("\r" + i + "%");
            Thread.sleep(1000);
        }
        System.out.println("done");
    }
}