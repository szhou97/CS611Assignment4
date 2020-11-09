public class GameInit {
    private final String[] weaponFiles = {"../resources/Weaponry.txt"};
    private final String[] armorFiles = {"../resources/Armory.txt"};
    private final String[] potionFiles = {"../resources/Potions.txt"};
    private final String[] spellFiles = {
        "../resources/IceSpells.txt",
        "../resources/FireSpells.txt",
        "../resources/LightningSpells.txt"};
    private final String[] heroFiles = {"../resources/Warriors.txt"};
    public GameInit() {

    }
    public void run() {
        FileScanner fs = new FileScanner(weaponFiles, armorFiles, 
                                        potionFiles, spellFiles, 
                                        null, null);
        
        fs.populateStock();
    }
}
