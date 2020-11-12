/**
 * This class loads the txt files into the program, and initialize the game 
 * accordingly
 */
public class GameInit {
    private final String[] weaponFiles = {"Weaponry.txt"};
    private final String[] armorFiles = {"Armory.txt"};
    private final String[] potionFiles = {"Potions.txt"};
    private final String[] spellFiles = {
        "IceSpells.txt",
        "FireSpells.txt",
        "LightningSpells.txt"
    };
    private final String[] heroFiles = {
        "Warriors.txt",
        "Sorcerers.txt",
        "Paladins.txt"
    };
    private final String[] monsterFiles = {
        "Exoskeletons.txt",
        "Dragons.txt",
        "Spirits.txt"
    };
    public GameInit() {

    }
    public void initializeGame() {
        FileScanner fs = new FileScanner(weaponFiles, armorFiles, 
                                        potionFiles, spellFiles, 
                                        heroFiles, monsterFiles);
        String[] options = {"start", "help", "quit"};
        boolean done = false;
        while (!done) {
            System.out.print(Messages.TITLE);
            System.out.print(Messages.WELCOME_MESSAGE);
            System.out.println(Messages.WELCOME_PROMPT);
            String selection = Controller.stringSelection(options);
            if (selection.equals("start")) {
                PlayerInit pi = new PlayerInit(fs.loadCharacters(heroFiles));
                Player player = pi.initPlayer();
                Grid world = new HeroAndLegendsWorld(10, player, 
                    fs.loadItems(), fs.loadCharacters(monsterFiles));
                Controller controller = new Controller(player, world);
                this.run(world, controller);
            } else if (selection.equals("help")) {
                System.out.println(Messages.HELP);
                Controller.pressEnter();
            } else if (selection.equals("quit")) {
                done = true;
            }
        }
    }

    public int run(Grid world, Controller controller) {
        boolean done = false;
        while(!done) {
            world.printGrid();
            if (controller.mainControll() == -1) {
                done = true;
            }
        }
        return 0;
    }
}
