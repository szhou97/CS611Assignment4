import java.util.ArrayList;

public class BattleGround {
    private ElementCollection heros;
    private ElementCollection monsters;
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

    private boolean attack(Hero hero, Monster monster, int damage) {

        if (this.dodged(monster, "monster")) {
            System.out.println("\n\t\tOh no, the monster dodged your attack");
        } else {
            this.executeAttack(hero, monster,  "monster", damage);
        }
        System.out.println("\n\t\tTHE MONSTER IS FIGHTING BACK");
        if (this.dodged(hero, "hero")) {
            System.out.println("\n\t\tYES! You have dodged a monster attack\n\n\n");
        } else {
            this.executeAttack(monster, hero, "hero", monster.getAttribute("damage"));
        }

        return true;
    }

    private boolean cast(Hero hero, Monster monster) {
        Query query = new Query();
        String[] types = {"spells"};
        Element element = query.processItemRequest(types, hero.getInventory());
        if (element == null) {
            return false;
        } else {
            Spell spell = (Spell) element;
            int manaCost = spell.getManaCost();
            int mana = hero.getAttribute("mana");
            if ((mana - manaCost) <= 0) {
                System.out.println("Insufficient mana");
                return false;
            } else {
                hero.changeAttribute("mana", mana - manaCost);
            }
            String affectedAttribute = spell.getAffectedAttribute();
            int baseValue = monster.getAttribute(affectedAttribute);
            int newValue = spell.reduceAttribute(baseValue);
            monster.changeAttribute(affectedAttribute, newValue);

            System.out.print("\n\t\t");
            this.printCurrentHero(hero, ColorScheme.ANSI_RED);
            System.out.print(" cast ");
            System.out.print(ColorScheme.ANSI_CYAN
                + spell.getType()
                + ColorScheme.ANSI_RESET);
            System.out.print("\n\t\t");
            this.printCurrentHero(monster, ColorScheme.ANSI_PURPLE);
            System.out.println("'s " + affectedAttribute + " reduced.");


            int dexterity = hero.getAttribute("dexterity");
            int spellDamage = spell.getDamage();
            int finalDamage = (int) (spellDamage 
                    + (dexterity/10000) * spellDamage);
            // Hero attacks
            this.attack(hero, monster, finalDamage);
            monster.changeAttribute(affectedAttribute, baseValue);
        }
        return true;
    }

    private void printCharacterDetail(Character character) {
        System.out.print(", health: " + character.getAttribute("health")
            + ", level: " + character.getAttribute("level")
            + ", defense: " + character.getDefense());
    }

    private void printCurrentHero(Character character, String color) {
        System.out.print(color  
            + character.getName()
            + ColorScheme.ANSI_RESET);
    }

    private void printCurrentBattle(Hero hero, Monster monster) {
            System.out.println("\t\tCurrent battle between: ");
            this.printCurrentHero(hero, ColorScheme.ANSI_RED);
            this.printCharacterDetail(hero);
            System.out.print("\n\n");
            this.printCurrentHero(monster, ColorScheme.ANSI_PURPLE);
            this.printCharacterDetail(monster);
            System.out.print("\n\n");
    }

    private void checkHero(Hero hero, int health, int mana,
                            ArrayList<Element> herolist) {

        int newHealth = hero.getAttribute("health");
        int newMana = hero.getAttribute("mana");
        int healthIncrease = (int) (health * 0.1);
        int manaIncrease = (int) (mana * 0.1);
        if (newHealth <= 0) {
            System.out.print("\n\t\t");
            this.printCurrentHero(hero, ColorScheme.ANSI_RED);
            System.out.println(" has fainted");
            herolist.remove(hero);
        } else if (newHealth + healthIncrease <= health){
            newHealth += healthIncrease;
            hero.changeAttribute("health", newHealth);
        }

        if (newMana + manaIncrease <= mana) {
            newMana += manaIncrease;
            hero.changeAttribute("mana", newMana);
        }
    }

    private void checkMonster(Monster monster, int health, 
        ArrayList<Element> monsterlist) {
        
        if (monster.getAttribute("health") <= 0) {
            System.out.print("\n\t\t");
            this.printCurrentHero(monster, ColorScheme.ANSI_PURPLE);
            System.out.println(" has been defeated");
            monsterlist.remove(monster);
        }
    }

    public ArrayList<Element> battle() {
        int heroIndex = 0, monsterIndex = 0;
        ArrayList<Element> herolist = this.heros.getAllElements();
        ArrayList<Element> monsterlist = this.monsters.getAllElements();
        int round = 1;
        this.printBattlefield();
        while (herolist.size() != 0 && monsterlist.size() != 0) {
            Controller.pressEnter();
            System.out.println(ColorScheme.ANSI_YELLOW 
                + "\n\n\n\nROUND " + round 
                + ColorScheme.ANSI_RESET);

            Hero hero = (Hero) herolist.get(heroIndex);
            Monster monster = (Monster) monsterlist.get(monsterIndex);
            
            this.printCurrentBattle(hero, monster);
            hero.printCurrentSetup();
            boolean complete = false;
            while (!complete) {
                System.out.println(Messages.MOVE_SELECTION);
                String[] options = Messages.MOVE_OPTIONS;
                String selection = Controller.stringSelection(options);
                if (selection.equals("attack")) { 
                    System.out.println("\t\t" 
                        + ColorScheme.ANSI_RED
                        + hero.getName()
                        + ColorScheme.ANSI_RESET
                        + " is attacking "
                        + ColorScheme.ANSI_PURPLE
                        + monster.getName()
                        + ColorScheme.ANSI_RESET);
                    complete = this.attack(hero, monster, hero.getDamage());
                    this.printCurrentBattle(hero, monster);
                } else if (selection.equals("equip") || selection.equals("unequip")) {
                    complete = hero.equipAnItem();
                } else if (selection.equals("cast")) {
                    complete = this.cast(hero, monster);
                    this.printCurrentBattle(hero, monster);
                } else if (selection.equals("use")) {
                    complete = hero.usePotion();
                }
            }
            this.checkHero(hero, hero.getHealth(), hero.getMana(), herolist);
            this.checkMonster(monster, monster.getHealth(), monsterlist);
            
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
        return herolist;
    }
}
