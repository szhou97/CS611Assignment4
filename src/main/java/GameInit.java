public class GameInit {
    private GameInit() {

    }
    public static void run() {
        Player player = new Player();
        Grid h = new HeroAndLegendsWorld(15, player);
        Controller ps = new Controller(h);
        while(true) {
            ps.changePositions();
            h.printGrid();
        }

    }
}
