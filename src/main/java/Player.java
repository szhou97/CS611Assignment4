import java.util.ArrayList;

public class Player implements Playable {
    private ArrayList<Hero> heros;
    private int xPosition;
    private int yPosition;
    public Player() {
        heros = new ArrayList<Hero>();
        this.xPosition = -1;
        this.yPosition = -1;
    }

    public void setInitialPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public void addHero(Hero hero) { 
        this.heros.add(hero);
        
    }

    public int getNumHeros() {
        return this.heros.size();
    }
    
    @Override
    public void viewInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public void wieldWeapon() {
        // TODO Auto-generated method stub

    }

    @Override
    public void wearArmor() {
        // TODO Auto-generated method stub

    }

    @Override
    public void castSpell() {
        // TODO Auto-generated method stub

    }

    @Override
    public void usePotion() {
        // TODO Auto-generated method stub

    }
}
