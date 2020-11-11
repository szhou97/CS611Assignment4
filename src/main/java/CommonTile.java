import java.util.ArrayList;

public class CommonTile extends ReachableTile {
    private ArrayList<Monster> monsters;
    private ElementCollection monsterPool;
    private Controller controller;
    public CommonTile(ElementCollection monsterPool) {
        this.type = " ";
        this.monsterPool = monsterPool;
        this.monsters = new ArrayList<Monster>();
    }

    private ArrayList<Monster> getValidMonsters(int level) {
        ArrayList<Monster> validMonsters = new ArrayList<Monster>();
        ArrayList<String> types = this.monsterPool.getTypes();
        for (String type : types) {
            ArrayList<Element> list = monsterPool.getElementList(type);
            for (Element element : list) {
                Monster monster = (Monster) element;
                if (monster.getLevel() == level) {
                    validMonsters.add(monster);
                }
            }
        }
        return validMonsters;
    }

    private void generateMonsters(int number, int level) {
        ArrayList<Monster> validMonsters = this.getValidMonsters(level);
        if (validMonsters.size() == 0) {
            return;
        } else {
            for (int i = 0; i < number; i++) {
                int index = ChanceGenerator.generateRandomNumber(validMonsters.size());
                this.monsters.add(validMonsters.get(index));
            }
        }
    }

    @Override
    public void arrive(Player player, boolean firstArrival) {
        this.player = player;
        this.playerExists = true;
        if (!firstArrival) {
            if (ChanceGenerator.generateChance(70)) {
                this.generateMonsters(player.getNumHeros(), player.highestLevel());
                if (monsters.size() != 0) {
                    BattleGround battleGround = new BattleGround(player.getHeros(), monsters);
                    battleGround.printBattlefield();
                }
            }
        } 
    }

    @Override
    public void leave() {
        this.player = null;
        this.playerExists = false;
        this.monsters = new ArrayList<Monster>();
    }

    @Override
    boolean reachable() {
        return true;
    }
}
