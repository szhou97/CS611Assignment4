import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileScanner {
    private Inventory stock;
    private HeroPool heros;
    private MonsterPool monsters;
    private String[] weaponFiles;
    private String[] armorFiles;
    private String[] potionFiles;
    private String[] spellFiles;
    private String[] heroFiles;
    private String[] monsterFiles;

    public FileScanner(String[] weaponFiles, String[] armorFiles, 
                        String[] potionFiles, String[] spellFiles,
                        String[] heroFiles, String[] monsterFiles) {
        this.weaponFiles = weaponFiles;
        this.armorFiles = armorFiles;
        this.potionFiles = potionFiles;
        this.spellFiles = spellFiles;
        this.heroFiles = heroFiles;
        this.monsterFiles = monsterFiles;
        this.stock = new Inventory();
    }

    private File[] createFiles(String[] filenames) {
        File[] files = new File[filenames.length];
        for (int i = 0; i < filenames.length; i++) {
            files[i] = new File(filenames[i]);
        }

        return files;
    }

    private String[] readFileLines(File[] files) throws FileNotFoundException {
        String[] lines = new String[50];
        int count = 0;
        for (int i = 0; i < files.length; i++) {
            Scanner scan = new Scanner(files[i]);
            while (scan.hasNextLine()) {
                lines[count] = scan.nextLine();
                count++;
            }
            scan.close();
        }
        return lines;
    }

    private void stockItems(String[] lines, String type) {
        for (int i = 1; i < lines.length; i++) {
            if (lines[i] != null) {
                String[] splitString = lines[i].split("\\s+");
                try {
                    String name = splitString[0].trim();
                    int price = Integer.parseInt(splitString[1]);
                    int minLevel = Integer.parseInt(splitString[2]);
                    switch(type) {
                        case "weapon":
                            int damage = Integer.parseInt(splitString[3]);
                            int hand = Integer.parseInt(splitString[4]);
                            Item weapon = new Weapon(name, price, minLevel, damage, hand);
                            this.stock.addWeapon(weapon);
                            break;
                        case "armor":
                            int damageRedux = Integer.parseInt(splitString[3]);
                            Item armor = new Armor(name, price, minLevel, damageRedux);
                            this.stock.addArmor(armor);
                            break;
                        case "potion":
                            int attIncrease = Integer.parseInt(splitString[3]);
                            String attribute = splitString[4];
                            Item potion = new Potion(name, price, minLevel, attIncrease, attribute)
                            this.stock.addPotion(potion);
                            break;
                        case "spell":
                            break;
                    }
                    
                } catch (NumberFormatException nfe) {
                    System.out.println("Error casting string to integer");
                }
            }
        }
    }


    public void populateStock() {
        File[] weapons = this.createFiles(this.weaponFiles);
        File[] armors = this.createFiles(this.armorFiles);
        File[] potions = this.createFiles(this.potionFiles);
        File[] spells = this.createFiles(this.spellFiles);
        try {
            String[] weaponLines = this.readFileLines(weapons);
            this.stockItems(weaponLines, "weapon");

            String[] armorLines = this.readFileLines(armors);
            this.stockItems(armorLines, "armor");

            String[] potionLines = this.readFileLines(potions);
            this.stockItems(potionLines, "potion");
            String[] spellLines = this.readFileLines(spells);
            this.stockItems(spellLines, "spell");
        } catch (FileNotFoundException fne) {
            System.out.println("File does not exist!!!");
        }
    }

    public HeroPool scanHeros() {
        return this.heros;
    }

    public MonsterPool scanMonsters() {
        return this.monsters;
    }

    public Inventory getStock() {
        return this.stock;
    }
}
