public class GameInit {
    private final String[] itemFiles = {
        "/src/main/resources/Armory.txt", 
        "/src/main/resources/Potions.txt",
        "/src/main/resources/Weaponry.txt",
        "/src/main/resources/IceSpells.txt",
        "/src/main/resources/FireSpells.txt",
        "/src/main/resources/LightningSpells.txt"
    };
    private GameInit() {

    }
    public void run() {
        Player player = new Player();
        Grid h = new HeroAndLegendsWorld(11, player);
        MainController ps = new MainController(h);
        Inventory stock = new MarketStock(itemFiles);
        while(true) {
            ps.changePositions();
            h.printGrid();
        }
    }
}
