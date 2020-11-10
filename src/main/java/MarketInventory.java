public class MarketInventory extends Inventory {
    public MarketInventory(
        ElementCollection weapons,
        ElementCollection armors,
        ElementCollection potions,
        ElementCollection spells
    ) 
    {
        this.inventory.put("weaponry", weapons);
        this.inventory.put("armory", armors);
        this.inventory.put("potions", potions);
        this.inventory.put("spells", spells);
    }
}
