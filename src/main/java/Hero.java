public abstract class Hero extends Character implements Playable{
    protected int strength, agility, wealth, experience;
    private PlayerInventory inventory;
    public Hero(String name, int level, int health, int strength, int agility,
                            int wealth, int experience) {
        super(name, level, health);
        this.attributes.add("strength", strength);
        this.attributes.add("agility", agility);
        this.attributes.add("wealth", wealth);
        this.attributes.add("experience", experience);
        this.inventory = new PlayerInventory();
    }


    @Override
    public void viewInventory() {
        
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
