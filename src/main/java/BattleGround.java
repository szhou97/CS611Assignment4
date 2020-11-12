import java.util.ArrayList;

public class BattleGround {
    private ElementCollection heros;
    private ElementCollection monsters;
    private Hero currentHero;
    private Monster currentMonster;
    private ArrayList<Element> herolist;
    private ArrayList<Element> monsterlist;
    public BattleGround(ElementCollection heros, ElementCollection monsters) {
        this.heros = heros;
        this.monsters = monsters;
    }

    private void printBattlefield() {
        System.out.println("\n\n\nHeros: ");
        heros.printElements(TypeInfo.HERO_BRACKET);

        System.out.println("\n\n\nMonsters: ");
        monsters.printElements(TypeInfo.MONSTER_BRACKET);
    }

    private boolean dodged(Character character, String type) {
        double dodgeValue = 0;
        if (type.equals("hero")) {
            dodgeValue = (double) (character.getAttribute("agility") * 0.002);
        } else {
            dodgeValue = (double) (character.getAttribute("dodge_chance") * 0.01);
        }
        return ChanceGenerator.generateChance((int)(dodgeValue));
    }

    private void executeAttack(Character source, Character target, 
                                String targetType, int damage) {
        int health = target.getAttribute("health");
        int newHealth = health;
        int defense = target.getDefense();
        int extraDamage = 0;
        if (damage < defense) {
            target.reduceDefense(damage);
        } else {
            target.reduceDefense(defense);
            extraDamage = damage - defense;
            newHealth = health - extraDamage;
        }
        target.changeAttribute("health", newHealth);
        System.out.print("\t\t");
        this.printCurrentHero(source, ColorScheme.ANSI_YELLOW);
        System.out.print(" dealt " + damage + " of damage to ");
        this.printCurrentHero(target, ColorScheme.ANSI_YELLOW);
        System.out.print("\n\n");
    }

    private boolean attack(int damage) {

        if (this.dodged(currentMonster, "monster")) {
            System.out.println("\n\t\tOh no, the monster dodged your attack");
        } else {
            this.executeAttack(currentHero, currentMonster,  "monster", damage);
            if (this.checkMonster()) {
                return true;
            }
        }
        System.out.println("\n\t\tTHE MONSTER IS FIGHTING BACK");
        if (this.dodged(currentHero, "hero")) {
            System.out.println("\n\t\tYES! You have dodged a monster attack\n\n\n");
        } else {
            this.executeAttack(currentMonster, currentHero, 
                "hero", currentMonster.getAttribute("damage"));
            if (this.checkHero()) {
                return true;
            }
        }

        return true;
    }

    private boolean cast() {
        Query query = new Query();
        String[] types = {"spells"};
        Element element = query.processItemRequest(types, currentHero.getInventory());
        if (element == null) {
            return false;
        } else {
            Spell spell = (Spell) element;
            int manaCost = spell.getManaCost();
            int mana = currentHero.getAttribute("mana");
            if ((mana - manaCost) <= 0) {
                System.out.println("Insufficient mana");
                return false;
            } else {
                currentHero.changeAttribute("mana", mana - manaCost);
            }
            String affectedAttribute = spell.getAffectedAttribute();
            int baseValue = currentMonster.getAttribute(affectedAttribute);
            int newValue = spell.reduceAttribute(baseValue);
            currentMonster.changeAttribute(affectedAttribute, newValue);

            System.out.print("\n\t\t");
            this.printCurrentHero(currentHero, ColorScheme.ANSI_RED);
            System.out.print(" cast ");
            System.out.print(ColorScheme.ANSI_CYAN
                + spell.getType()
                + ColorScheme.ANSI_RESET);
            System.out.print("\n\t\t");
            this.printCurrentHero(currentMonster, ColorScheme.ANSI_PURPLE);
            System.out.println("'s " + affectedAttribute + " reduced.");


            int dexterity = currentHero.getAttribute("dexterity");
            int spellDamage = spell.getDamage();
            int finalDamage = (int) (spellDamage 
                    + (dexterity/10000) * spellDamage);
            // Hero attacks
            this.attack(finalDamage);
            currentMonster.changeAttribute(affectedAttribute, baseValue);
        }
        return true;
    }

    private void printCharacterDetail(Character character) {
        System.out.print(", health: " + character.getAttribute("health")
            + ", level: " + character.getAttribute("level")
            + ", defense: " + character.getDefense());
    }

    private void printCurrentHero(Character character, String color) {
        System.out.print("\n\t\t" + color  
            + character.getName()
            + ColorScheme.ANSI_RESET);
    }

    private void printCurrentBattle() {
            System.out.println("\n\n\t\tCurrent battle between: ");
            this.printCurrentHero(currentHero, ColorScheme.ANSI_RED);
            this.printCharacterDetail(currentHero);
            System.out.print("\n\n");
            this.printCurrentHero(currentMonster, ColorScheme.ANSI_PURPLE);
            this.printCharacterDetail(currentMonster);
            System.out.print("\n\n");
    }

    private boolean checkHero() {
        int health = currentHero.getHealth();
        int mana = currentHero.getMana();
        int newHealth = currentHero.getAttribute("health");
        int newMana = currentHero.getAttribute("mana");
        int healthIncrease = (int) (health * 0.1);
        int manaIncrease = (int) (mana * 0.1);
        if (newHealth <= 0) {
            System.out.print("\n\t\t");
            this.printCurrentHero(currentHero, ColorScheme.ANSI_RED);
            System.out.println(" has fainted");
            herolist.remove(currentHero);
            return true;
        } else if (newHealth + healthIncrease <= health){
            newHealth += healthIncrease;
            currentHero.changeAttribute("health", newHealth);
        }

        if (newMana + manaIncrease <= mana) {
            newMana += manaIncrease;
            currentHero.changeAttribute("mana", newMana);
        }
        return false;
    }

    private boolean checkMonster() {
        
        if (currentMonster.getAttribute("health") <= 0) {
            System.out.print("\n\t\t");
            this.printCurrentHero(currentMonster, ColorScheme.ANSI_PURPLE);
            System.out.println(" has been defeated");
            monsterlist.remove(currentMonster);
            return true;
        } else {
            return false;
        }
    }

    public void battle() {
        System.out.println(Messages.BATTLEFIELD);
        int heroIndex = 0, monsterIndex = 0;
        this.herolist = this.heros.getAllElements();
        this.monsterlist = this.monsters.getAllElements();
        int round = 1;
        this.printBattlefield();
        while (herolist.size() != 0 && monsterlist.size() != 0) {
            Controller.pressEnter();
            System.out.println(ColorScheme.ANSI_YELLOW 
                + "\n\n\n\nROUND " + round 
                + ColorScheme.ANSI_RESET);

            currentHero = (Hero) herolist.get(heroIndex);
            currentMonster = (Monster) monsterlist.get(monsterIndex);
            
            this.printCurrentBattle();
            currentHero.printCurrentSetup();
            boolean complete = false;
            while (!complete) {
                String[] options = Messages.MOVE_OPTIONS;
                System.out.println("Choose your move. Select 'h' for help information");
                String selection = Controller.stringSelection(options);
                if (selection.equals("a")) { 
                    System.out.println("\t\t" 
                        + ColorScheme.ANSI_RED
                        + currentHero.getName()
                        + ColorScheme.ANSI_RESET
                        + " is attacking "
                        + ColorScheme.ANSI_PURPLE
                        + currentMonster.getName()
                        + ColorScheme.ANSI_RESET);
                    complete = this.attack(currentHero.getDamage());
                    this.printCurrentBattle();
                } else if (selection.equals("e")) {
                    complete = currentHero.equipAnItem();
                } else if (selection.equals("c")) {
                    complete = this.cast();
                    this.printCurrentBattle();
                } else if (selection.equals("u")) {
                    complete = currentHero.usePotion();
                } else if (selection.equals("i")) {
                    System.out.print(ColorScheme.ANSI_YELLOW
                        + "\n\n\nPrinting current inventory: "
                        + ColorScheme.ANSI_RESET);
                    System.out.println(ColorScheme.ANSI_RED
                        + currentHero.getName()
                        + ColorScheme.ANSI_RESET);
                    currentHero.getInventory().printInventory();
                    currentHero.printCurrentSetup();
                } else if (selection.equals("h")) {
                    System.out.println(Messages.HELP);
                } else if (selection.equals("q")) {
                    System.out.println("Fight abandoned. "
                        + "The village has been distroyed by the monsters");
                    System.exit(0);
                }
            
                heroIndex++;
                monsterIndex++;
                if (heroIndex >= herolist.size()) {
                    heroIndex = 0;
                    round++;
                }
                if (monsterIndex >= monsterlist.size()) {
                    monsterIndex = 0;
                }
            }
        }
    }
}
