import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This class reads the files and put the elements of the file into the program's
 * data structure
 */
public class FileScanner {
    private String[] weaponFiles;
    private String[] armorFiles;
    private String[] potionFiles;
    private String[] spellFiles;

    public FileScanner(String[] weaponFiles, String[] armorFiles, 
                        String[] potionFiles, String[] spellFiles,
                        String[] heroFiles, String[] monsterFiles) {
        this.weaponFiles = weaponFiles;
        this.armorFiles = armorFiles;
        this.potionFiles = potionFiles;
        this.spellFiles = spellFiles;
    }

    private String fileName(String filePath) {
        String[] strSplit = filePath.split("/");
        String fileName = strSplit[strSplit.length - 1].replace(".txt", "");
        fileName = fileName.toLowerCase();
        return fileName;
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

    private void addItem(ElementCollection collection, 
                    String[] splitString, String fileName,
                    String[] categories) {
        try {
            String name = splitString[0].trim();
            int price = Integer.parseInt(splitString[1]);
            int minLevel = Integer.parseInt(splitString[2]);
            int damage = Integer.parseInt(splitString[3]);
            if (fileName.contains("weaponry")) {
                int hand = Integer.parseInt(splitString[4]);
                Item weapon = new Weapon(categories, fileName, name, price, minLevel, damage, hand);
                collection.add(weapon);;
            } else if (fileName.contains("armory")) {
                Item armor = new Armor(categories, fileName, name, price, minLevel, damage);
                collection.add(armor);
            } else if (fileName.contains("potions")) {
                String attribute = splitString[4];
                Item potion = new Potion(categories, fileName, name, price, minLevel, damage, attribute);
                collection.add(potion);
            } else if (fileName.contains("spell")) {
                int manaCost = Integer.parseInt(splitString[4]);
                Item spell = new Spell(categories, fileName, name, price, minLevel, damage, manaCost);
                collection.add(spell);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error casting string to integer");
        }
    }

    private void addCharacter(ElementCollection collection, 
                    String[] splitString, String fileName,
                    String[] categories) {
        try {
            String name = splitString[0].trim();
            if (splitString.length == 7) {
                int mana = Integer.parseInt(splitString[1]);
                int strength = Integer.parseInt(splitString[2]);
                int agility = Integer.parseInt(splitString[3]);
                int dexterity = Integer.parseInt(splitString[4]);
                int wealth = Integer.parseInt(splitString[5]);
                int exp = Integer.parseInt(splitString[6]);
                Character hero = new Hero(categories, fileName, name, 1, mana, strength, 
                                        agility, dexterity, wealth, exp);
                collection.add(hero);
            } else if (splitString.length == 5) {
                int level = Integer.parseInt(splitString[1]);
                int damage = Integer.parseInt(splitString[2]);
                int defense = Integer.parseInt(splitString[3]);
                int dodgeChance = Integer.parseInt(splitString[4]);
                Character monster = new Monster(categories, fileName, name, level, damage, 
                                        defense, dodgeChance);
                collection.add(monster);
            }
            
        } catch (NumberFormatException nfe) {
            System.out.println("Error casting string to integer");
        }
    }

    private void addElement(ElementCollection collection, 
                    String[] splitString, String fileName,
                    String objectType, String[] categories) {
        switch(objectType) {
            case "item":
                this.addItem(collection, splitString, fileName, categories);
                break;
            case "character":
                this.addCharacter(collection, splitString, fileName, categories);
                break;
        }
        
    }

    private ElementCollection processFiles(String[] filePaths, String objectType) {
        ElementCollection collection = new ElementCollection();
        for (String filePath : filePaths) {
            String[] lines = this.readFileLines(filePath);
            String fileName = this.fileName(filePath);
            String firstLine = lines[0];
            String[] categories = firstLine.split("/");
            
            for (String line : lines) {
                if (line == lines[0]) {
                    continue;
                }
                if (line != null) {
                    String[] splitLine = line.split("\\s+");
                    this.addElement(collection, splitLine, fileName, objectType, categories);
                }
            }
        }
        return collection;
    }
    
    public Inventory loadItems() {
        ElementCollection weapons = this.processFiles(this.weaponFiles, "item");
        ElementCollection armors = this.processFiles(this.armorFiles, "item");
        ElementCollection potions = this.processFiles(this.potionFiles, "item");
        ElementCollection spells = this.processFiles(this.spellFiles, "item");
        Inventory stock = new MarketInventory(weapons, armors, potions, spells);
        return stock;
    }

    public ElementCollection loadCharacters(String[] files) {
        ElementCollection characters = this.processFiles(files, "character");
        String[] cat = characters.getCategories();
        String[] newCat = new String[cat.length + 2];
        newCat[0] = cat[0];
        for (int i = 1; i < cat.length; i++) {
            newCat[i + 2] = cat[i];
        }

        newCat[1] = "level";
        newCat[2] = "health";
        characters.setCategories(newCat);
        return characters;
    }
}
