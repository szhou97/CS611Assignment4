import java.util.ArrayList;
public class BattleGround {
    private ElementCollection heros;
    private ElementCollection monsters;

    public BattleGround(ArrayList<Hero> herolist, ArrayList<Monster> monsterlist) {
        this.heros = new ElementCollection();
        this.monsters = new ElementCollection();
        for (Hero hero : herolist) {
            heros.add(hero);
        }
        
        for (Monster monster : monsterlist) {
            monsters.add(monster);
        }
    }

    private void heroMove(Hero hero, Monster monster) {

    }

    public void printBattlefield() {
        System.out.println("\n\n\nHeros: ");
        heros.printElements(TypeInfo.HERO_BRACKET);

        System.out.println("\n\n\nMonsters: ");
        monsters.printElements(TypeInfo.MONSTER_BRACKET);
    }

    public void battle() {
        int heroIndex = 0, monsterIndex = 0;
       
    }
}
