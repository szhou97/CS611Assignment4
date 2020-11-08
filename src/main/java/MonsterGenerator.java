import java.util.ArrayList;
import java.util.Random;

public final class MonsterGenerator {
    private ArrayList<Monster> monsters;
    public MonsterGenerator(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public Monster generateMonster() {
        Random random = new Random();
        return this.monsters.get(random.nextInt(this.monsters.size()));
    }

    public boolean monsterPercentage() {
        return ChanceGenerator.generateChance(80);
    }
}
