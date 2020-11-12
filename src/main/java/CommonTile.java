import java.util.ArrayList;

public class CommonTile extends ReachableTile {
    private ElementCollection monsterPool;

    public CommonTile(ElementCollection monsterPool) {
        this.type = " ";
        this.monsterPool = monsterPool;
    }

    private ArrayList<Monster> getValidMonsters(int level) {
        ArrayList<Monster> validMonsters = new ArrayList<Monster>();
        ArrayList<String> types = this.monsterPool.getTypes();
        for (String type : types) {
            ArrayList<Element> list = monsterPool.getElementList(type);
            for (Element element : list) {
                Monster monster = (Monster) element;
                if (monster.getAttribute("level") == level) {
                    validMonsters.add(monster);
                }
            }
        }
        return validMonsters;
    }

    private ElementCollection generateMonsters(int number, int level) {
        ArrayList<Monster> validMonsters = this.getValidMonsters(level);
        if (validMonsters.size() == 0) {
            return null;
        } else {
            ElementCollection monsters = new ElementCollection();
            for (int i = 0; i < number; i++) {
                int index = ChanceGenerator.generateRandomNumber(validMonsters.size());
                monsters.add(validMonsters.get(index));
            }
            return monsters;
        }
    }

    @Override
    public void arrive(Player player, boolean firstArrival) {
        this.player = player;
        this.playerExists = true;
        if (!firstArrival) {
            if (ChanceGenerator.generateChance(50)) {
                int numHeros = player.getNumHeros();
                int highestLevel = player.getHighestLevel();
                ElementCollection monsters = this.generateMonsters(numHeros, highestLevel);
                monsters.setCategories(monsterPool.getCategories());
                if (monsters != null) {
                    BattleGround battleGround = new BattleGround(player.getHeros(), monsters);
                    battleGround.battle();
                    this.player.updateHeros(highestLevel*100);
                }
            }
        } 
    }

    @Override
    public void leave() {
        this.player = null;
        this.playerExists = false;
    }

    @Override
    boolean reachable() {
        return true;
    }
}
