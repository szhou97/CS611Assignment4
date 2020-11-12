import java.util.ArrayList;

public class Player {
    private ElementCollection heros;
    public Player() {
        heros = new ElementCollection();
    }

    public ElementCollection getHeros() {
        return this.heros;
    }
    
    public void addHero(Element hero) { 
        this.heros.add((Hero) hero);
    }

    public int getNumHeros() {
        return this.heros.getTotalNumElements();
    }

    public int getHighestLevel() {
        ArrayList<Element> currentHeros = heros.getAllElements();
        int level = 0;
        int tmp = 0;
        for (Element hero : currentHeros) {
            tmp = ((Hero) hero).getAttribute("level");
            if (level < tmp) {
                level = tmp;
            }
        }
        return level;
    }

    public void viewHeroInventory() {
        Hero hero = this.selectHero();
        hero.getInventory().printInventory();
    }

    public Hero selectHero() {
        Query query = new Query();
        System.out.println(ColorScheme.ANSI_YELLOW
            + "Select a hero"
            + ColorScheme.ANSI_RESET);
        Hero hero = (Hero) query.getItemList(this.heros);
        return hero;
    }

    public void printHeros() {
        this.heros.printElements(TypeInfo.HERO_BRACKET);
    }

    public void setCategories(String[] categories) {
        this.heros.setCategories(categories);
    }

    public void updateHeros(int levelCoins) {
        ArrayList<String> types = this.heros.getTypes();
        for (String type : types) {
            ArrayList<Element> herolist = this.heros.getElementList(type);
            for (Element element : herolist) {
                Hero hero = (Hero) element;
                int level = hero.getAttribute("level");
                if (hero.getAttribute("health") > 0) {
                    int exp = hero.getAttribute("experience") + 2;
                    
                    int wealth = hero.getAttribute("wealth") + levelCoins;
                    hero.changeAttribute("experience", exp);
                    hero.changeAttribute("wealth", wealth);
                    if ( (exp - hero.getExp()) >= level*10 ) {
                        System.out.print("\t\tHero " 
                            + ColorScheme.ANSI_RED
                            + hero.getName()
                            + ColorScheme.ANSI_RESET
                            + " has leveled up!! ");
                        hero.levelUp();
                    }
                } else {
                    hero.reset();
                }
            }
        }
    }
}
