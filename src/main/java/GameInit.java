import java.lang.ProcessBuilder.Redirect.Type;

public class GameInit {
    private final String[] weaponFiles = {"../resources/Weaponry.txt"};
    private final String[] armorFiles = {"../resources/Armory.txt"};
    private final String[] potionFiles = {"../resources/Potions.txt"};
    private final String[] spellFiles = {
        "../resources/IceSpells.txt",
        "../resources/FireSpells.txt",
        "../resources/LightningSpells.txt"
    };
    private final String[] heroFiles = {
        "../resources/Warriors.txt",
        "../resources/Sorcerers.txt",
        "../resources/Paladins.txt"
    };
    private final String[] monsterFiles = {
        "../resources/Exoskeletons.txt",
        "../resources/Dragons.txt",
        "../resources/Spirits.txt"
    };
    public GameInit() {

    }
    public void initializeGame() {
        FileScanner fs = new FileScanner(weaponFiles, armorFiles, 
                                        potionFiles, spellFiles, 
                                        heroFiles, monsterFiles);
        System.out.print(Messages.TITLE);
        System.out.print(Messages.WELCOME_MESSAGE);
        System.out.println(Messages.WELCOME_PROMPT);
        String[] options = {"start", "help"};
        String selection = Controller.stringSelection(options);
        System.out.println(selection);
        if (selection.equals("start")) {
            PlayerInit pi = new PlayerInit(fs.populateHeroPool());
            Player player = pi.initPlayer();
            Grid world = new HeroAndLegendsWorld(10, player, 
                fs.populateStock(), fs.populateMonsterPool());
            Controller controller = new Controller(world);
            this.run(world, controller);
        }
    }

    public void run(Grid world, Controller controller) {
        while(true) {
            world.printGrid();
            controller.mainControll();
        }
    }
}
