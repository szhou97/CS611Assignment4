import java.util.Random;

public class MonsterPool extends CharacterPool {

    public Monster generateMonster() {
        Random random = new Random();
        int rand = random.nextInt(this.characters.size());
        return (Monster) this.characters.get(rand);
    }

    public boolean monsterPercentage() {
        return ChanceGenerator.generateChance(80);
    }
}
