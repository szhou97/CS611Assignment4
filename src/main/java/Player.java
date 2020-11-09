import java.util.ArrayList;

public class Player implements Playable {
    private ArrayList<Hero> heros;
    public Player() {
        heros = new ArrayList<Hero>();
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
