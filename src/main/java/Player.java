import java.util.ArrayList;

public class Player {
    private ArrayList<Hero> heros;
    public Player() {
        this.heros = new ArrayList<Hero>();
    }

    public ArrayList<Hero> getHeros() {
        return this.heros;
    }

    public void printBasicInfo() {
        System.out.println("                                   "
            + "Current team members");
        System.out.println("name" + "\t\t\t\t\t\t" + "type");
        System.out.println(TypeInfo.HERO_BRACKET);
        int index = 1;
        for (Hero hero : heros) {
            System.out.println(index + ", " + hero.getName() + "\t\t\t\t" + hero.getType());
            index++;
        }
    }

    public void printWealth() {
        System.out.println("                                   "
            + "Current team members");
        System.out.println("name" + "\t\t\t\t\t\t" + "money");
        System.out.println(TypeInfo.HERO_BRACKET);
        int index = 1;
        for(Hero hero : heros) {
            System.out.println(index + ", " + hero.getName() + "\t\t\t\t$" + hero.getWealth());
            index++;
        }
    }
    
    public void addHero(Element hero) { 
        this.heros.add((Hero) hero);
    }

    public int getNumHeros() {
        return this.heros.size();
    }

    public int highestLevel() {
        int level = 0;
        int tmp = 0;
        for (Hero hero : heros) {
            tmp = hero.getLevel();
            if (level < tmp) {
                level = tmp;
            }
        }
        return level;
    }
}
