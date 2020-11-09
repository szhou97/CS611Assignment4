import java.io.File;
import java.io.FileNotFoundException;
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
        this.heros = new HeroPool();
        this.monsters = new MonsterPool();
        this.stock = new Inventory();
    }

    private String fileName(String filePath) {
        String[] strSplit = filePath.split("/");
        String fileName = strSplit[strSplit.length - 1].replace(".txt", "");
        fileName = fileName.toLowerCase();
        return fileName;
    }

    private String[] splitLine(String line) {
        return line.split("\\s+");
    }

    private String[] readFileLines(String filePath) {
        File file = new File(filePath);
        String[] lines = new String[50];
        int count = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                lines[count] = scan.nextLine();
                count++;
            }
            scan.close();
        } catch (FileNotFoundException fne) {
            System.out.println("File does not exist!!!");
        }
        return lines;
    }

    private void stockItem(String[] splitString, String fileName) {
        try {
            String name = splitString[0].trim();
            int price = Integer.parseInt(splitString[1]);
            int minLevel = Integer.parseInt(splitString[2]);
            int damage = Integer.parseInt(splitString[3]);

            if (fileName.contains("weaponry")) {
                int hand = Integer.parseInt(splitString[4]);
                Item weapon = new Weapon(name, price, minLevel, damage, hand);
                this.stock.addWeapon(weapon);
            } else if (fileName.contains("armory")) {
                Item armor = new Armor(name, price, minLevel, damage);
                this.stock.addArmor(armor);
            } else if (fileName.contains("potions")) {
                String attribute = splitString[4];
                Item potion = new Potion(name, price, minLevel, damage, attribute);
                this.stock.addPotion(potion);
            } else if (fileName.contains("spell")) {
                int manaCost = Integer.parseInt(splitString[4]);
                Item spell = new Spell(name, price, minLevel, damage, manaCost, fileName);
                this.stock.addSpell(spell);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error casting string to integer");
        }
    }

    private void addCharacter(String[] splitString, String fileName) {
        try {
            String name = splitString[0].trim();
            if (splitString.length == 7) {
                int mana = Integer.parseInt(splitString[1]);
                int strength = Integer.parseInt(splitString[2]);
                int agility = Integer.parseInt(splitString[3]);
                int dexterity = Integer.parseInt(splitString[4]);
                int wealth = Integer.parseInt(splitString[5]);
                int exp = Integer.parseInt(splitString[6]);
                Character hero = new Hero(fileName, name, 1, 100, mana, strength, 
                                        agility, dexterity, wealth, exp);
                this.heros.addCharacter(hero);
            } else if (splitString.length == 5) {
                int level = Integer.parseInt(splitString[1]);
                int damage = Integer.parseInt(splitString[2]);
                int defense = Integer.parseInt(splitString[3]);
                int dodgeChance = Integer.parseInt(splitString[4]);
                Character monster = new Monster(fileName, name, level, 100, damage, 
                                        defense, dodgeChance);
                this.monsters.addCharacter(monster);
            }
            
        } catch (NumberFormatException nfe) {
            System.out.println("Error casting string to integer");
        }
    }

    private void processFiles(String[] filePaths, String objectType) {
        for (String filePath : filePaths) {
            String[] lines = this.readFileLines(filePath);
            String fileName = this.fileName(filePath);
            for (String line : lines) {
                if (line == lines[0]) continue;
                if (line != null) {
                    String[] splitLine = this.splitLine(line);
                    switch(objectType) {
                        case "item":
                            this.stockItem(splitLine, fileName);
                            break;
                        case "character":
                            this.addCharacter(splitLine, fileName);
                            break;
                    }
                }
                
            }
        }
    }
    
    public Inventory populateStock() {
        this.processFiles(this.weaponFiles, "item");
        this.processFiles(this.armorFiles, "item");
        this.processFiles(this.potionFiles, "item");
        this.processFiles(this.spellFiles, "item");
        return this.stock;
    }

    public HeroPool populateHeroPool() {
        this.processFiles(this.heroFiles, "character");
        return this.heros;
    }

    public MonsterPool populateMonsterPool() {
        this.processFiles(this.monsterFiles, "character");
        return this.monsters;
    }
}
