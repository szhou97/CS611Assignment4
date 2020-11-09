import java.util.ArrayList;

public abstract class CharacterPool {
    protected ArrayList<Character> characters;
    public CharacterPool() {
        characters = new ArrayList<Character>();
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public void printCharacters() {
        System.out.println(this.characters.size());
    }
}
