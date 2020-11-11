public class Hero extends Character implements Playable{
    protected int strength, agility, wealth, experience;
    private PlayerInventory inventory;
    public Hero(String[] categories, String type, String name, 
                int level, int health, int mana, 
                int strength, int agility, int dexterity, 
                int wealth, int experience) {

        super(categories, type, name, level, health);
        this.attributes.add("mana", mana);
        this.attributes.add("strength", strength);
        this.attributes.add("agility", agility);
        this.attributes.add("dexterity", dexterity);
        this.attributes.add("wealth", wealth);
        this.attributes.add("experience", experience);
        this.inventory = new PlayerInventory();
    }


    public Inventory getInventory() {
        return this.inventory;
    }

    public void buy(Item item) {
        int wealth = this.getWealth();
        this.attributes.replace("wealth", wealth - item.getPrice());
        this.inventory.add(item);
    }

    public void sell(Item item) {
        int wealth = this.getWealth();
        this.attributes.replace("wealth", wealth + 1/2 * item.getPrice());
        this.inventory.remove(item);
    }

    public int getWealth() {
        return this.attributes.get("wealth");
    }

    @Override
    public void viewInventory() {
        this.inventory.printInventory();
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
