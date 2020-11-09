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

    private void stockWeapon(String[] weapons) {
        for (int i = 1; i < weapons.length; i++) {
            if (weapons[i] != null) {
                String[] splitString = weapons[i].split(" ");
                try {
                    String name = splitString[0].trim();
                    int price = Integer.parseInt(splitString[1]);
                    int minLevel = Integer.parseInt(splitString[2]);
                    int damage = Integer.parseInt(splitString[3]);
                    int hand = Integer.parseInt(splitString[4]);
                    Item weapon = new Weapon(name, price, minLevel, damage, hand);
                    this.stock.addWeapon(weapon);
                } catch (NumberFormatException nfe) {
                    System.out.println("Error casting string to integer")
                }
            }
        }
    }

    private void stockArmor(String[] armors) {
        
    }

    private void stockPotion(String[] potions) {

    }

    private void stockSpell(String[] spells) {

    }


    public void populateStock() {
        File[] weapons = this.createFiles(this.weaponFiles);
        File[] armors = this.createFiles(this.armorFiles);
        File[] potions = this.createFiles(this.potionFiles);
        File[] spells = this.createFiles(this.spellFiles);
        try {
            String[] weaponLines = this.readFileLines(weapons);
            this.stockWeapon(weaponLines);
            String[] armorLines = this.readFileLines(armors);
            this.stockArmor(armorLines);
            String[] potionLines = this.readFileLines(potions);
            this.stockPotion(potionLines);
            String[] spellLines = this.readFileLines(spells);
            this.stockSpell(spellLines);
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
}
