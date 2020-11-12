import java.util.ArrayList;

public class Hero extends Character {
    private final String[] abilityttributes = {
        "mana", "strength", "agility", "dexterity"
    };
    private PlayerInventory inventory;
    private PlayerInventory equipments;
    private int numArmors, availableHands;
    private final int numArmorsAllowed = 1;
    private int mana, defense, experience;
    public Hero(String[] categories, String type, String name, 
                int level, int mana, 
                int strength, int agility, int dexterity, 
                int wealth, int experience) {

        super(categories, type, name, level);
        this.attributes.add("mana", mana);
        this.attributes.add("strength", strength);
        this.attributes.add("agility", agility);
        this.attributes.add("dexterity", dexterity);
        this.attributes.add("wealth", wealth);
        this.attributes.add("experience", experience);
        
        this.inventory = new PlayerInventory(TypeInfo.ALL_TYPES);
        this.equipments = new PlayerInventory(TypeInfo.EQUIPABLE_TYPES);
        this.numArmors = 0;
        this.availableHands = 2;
        this.mana = mana;
        this.experience = experience;
    }

    // Warriors: strength, agility
    // Sorcerers: dexterity, agility
    // Paladins: strength, dexterity

    public void update(String attribute, double multiplier) {
        int value = (int) (this.getAttribute(attribute) * multiplier);
        this.changeAttribute(attribute, value);
    }

    public void levelUp() {
        double normal = 1.05;
        double favored = 1.1;
        int level = this.getAttribute("level") + 1;
        this.experience = this.getAttribute("experience");
        this.mana = (int) (this.mana * 1.1);
        this.changeAttribute("level", level);
        switch (this.getType()) {
            case "warriors":
                this.update("strength", favored);
                this.update("agility", favored);
                this.update("dexterity", normal);
                break;
            case "sorcerers":
                this.update("strength", normal);
                this.update("agility", favored);
                this.update("dexterity", favored);
                break;
            case "paladins":
                this.update("strength", favored);
                this.update("agility", normal);
                this.update("dexterity", favored);
                break; 
        }
    }

    public int getMana() {
        return this.mana;
    }

    public int getExp() {
        return this.experience;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    private void moveItem(Item item, 
        PlayerInventory source, PlayerInventory target) {
        target.add(item);
        source.remove(item);
    }

    public void buy(Item item) {
        int wealth = this.getAttribute("wealth");
        this.attributes.replace("wealth", wealth - item.getAttribute("price"));
        this.inventory.add(item);
    }

    public void sell(Item item) {
        int newWealth = this.getAttribute("wealth") + (int) (0.5 * item.getAttribute("price"));
        this.attributes.replace("wealth", newWealth);
        this.inventory.remove(item);
    }

    private void increaseAttribute(String[] attributes, int increase) {
        for (String attribute : attributes) {
            System.out.println(attribute);
            if (attribute.equals("health")) {
                int baseHealth = this.getAttribute("health");
                this.attributes.replace("health", baseHealth + increase);
            } else {
                int originalAttribute = this.attributes.get(attribute);
                this.attributes.replace(attribute, originalAttribute + increase);
            }
        }
    }

    public boolean usePotion() {
        Query iq = new Query();
        ElementCollection collection = this.inventory.getCollection("potions");
        if (collection != null) {
            Element element = iq.getItemList(collection);
            if (element != null) {
                Potion potion = (Potion) element;
                String att = potion.getAttributeAffected().toLowerCase();
                int attIncrease = potion.getAttributeIncrease();
        
                if (att.equals("all")) {
                    this.increaseAttribute(this.abilityttributes, attIncrease);
                } else {
                    this.increaseAttribute(att.split("/"), attIncrease);
                }
                this.inventory.remove(potion);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean equipWeapon(Weapon weapon) {
        if (availableHands < weapon.getNumberHands()) {
            System.out.println(ColorScheme.ANSI_RED
                + "No more spare hands. "
                + "Remove a weapon before equiping"
                + ColorScheme.ANSI_RESET);
            return false;
        } else {
            this.moveItem(weapon, inventory, equipments);
            this.availableHands -= weapon.getNumberHands();
            return true;
        }
    }
    
    private void unequipWeapon(Weapon weapon) {
        this.moveItem(weapon, equipments, inventory);
        this.availableHands += weapon.getNumberHands();
    }

    private boolean equipArmor(Armor armor) {
        if (numArmors < numArmorsAllowed) {
            this.moveItem(armor, inventory, equipments);
            this.updateDefense();
            return true;
        } else {
            System.out.println(ColorScheme.ANSI_RED
                + "Please remove an armor to equip a new one"
                + ColorScheme.ANSI_RESET);
            return false;
        }
    }

    private void unequipArmor(Armor armor) {
        this.moveItem(armor, equipments, inventory);
        this.numArmors--;
        this.updateDefense();
    }

    private boolean equipPoll(Item item, int selection) {
        String type = item.getType();
        if (selection == 1) {
            if (type.equals("weaponry")) {
                return this.equipWeapon((Weapon) item);
            } else if (type.equals("armory")){
                return this.equipArmor((Armor) item);
            } else {
                return false;
            }
        } else if (selection == 2) {
            if (type.equals("weaponry")) {
                this.unequipWeapon((Weapon) item);
            } else if (type.equals("armory")) {
                this.unequipArmor((Armor) item);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean equipAnItem() {
        this.printCurrentSetup();
        Query iq = new Query();
        int selection = 0;
        if (equipments.isEmpty()) {
            selection = 1;
        } else {
            System.out.println("\n\nSelect 1: equip, 2:unequip\n");
            selection = Controller.intSelection(0, 2);
        }

        if (selection == 0) {
            return false;
        } else {
            Item item = null;
            if (selection == 1) {
                item = (Item) iq.processItemRequest(equipments.getTypes(), inventory);
            } else {
                item = (Item) iq.processItemRequest(equipments.getTypes(), equipments);
            }
            if (item == null) {
                return false;
            } else {
                System.out.println("\n" + item.getName() + " selected");
                return this.equipPoll(item, selection);
            }
        }
    }
    
    public void printCurrentSetup() {
        System.out.print(ColorScheme.ANSI_YELLOW
            + "\n\n\nPrinting current equipped items for: "
            + ColorScheme.ANSI_RESET);
        System.out.println(ColorScheme.ANSI_RED
            + this.getName()
            + ColorScheme.ANSI_RESET);
        this.equipments.printInventory();
    }

    public void updateDefense() {
        this.defense = 0;
        ElementCollection armories = this.equipments.getCollection("armory");
        ArrayList<Element> armors = armories.getAllElements();
        for (Element armor : armors) {
            this.defense += armor.getAttribute("damage_reduction");
        }
    }

    @Override
    public int getDamage() {
        int damage = 0;
        int strength = this.getAttribute("strength");
        ElementCollection weaponries = this.equipments.getCollection("weaponry");
        ArrayList<Element> weapons = weaponries.getAllElements();
        for (Element weapon : weapons) {
            damage += weapon.getAttribute("damage");
        }
        return (int) ((strength + damage) * 0.05);
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public void reduceDefense(int damage) {
        this.defense -= damage;
    }

    @Override
    public void reset() {
        int health = (int) this.getHealth() / 2;
        int mana = (int) this.getMana() / 2;
        this.changeAttribute("health", health);
        this.changeAttribute("mana", mana);
    }
}
