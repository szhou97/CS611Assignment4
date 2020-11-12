Project: Heros and Legends 1
Author: Shuaike (Shawn) Zhou


Execution instruction:
```bash
javac Main.java
java Main
```

Object Structure:
Element.java\
    Item.java\
        Weapon.java\
        Armor.java\
        Potions.java\
        Spell.java\
    Character.java\
        Hero.java\
        Monster.java\

Inventory.java\
    PlayerInventory.java\
    MarketInventory.java\

Tile.java\
    ReachableTile.java\
        CommonTile.java\
        Market.java\
    UnreachableTile.java\

Grid.java\
    HerosAndLegendsWorld.java\

Explanation:
    The Element.java class is the super class of every item/hero/monster in 
        this program. The element class contains an Attributes object which stores
        String/Integer pairs. When subclasses objects are created, they add their 
        specific information to this Attributes object. 

    The Tile.java class is the base of a nxn game grid. The subclasses of Tile.java
        contains specific information that allows them to be markets and battlefields

    The ElementCollection class is the basic data structure of any Elements in the
        game. This includes heros/monsters/items. The Inventory.java is used to store
        items, and the Query class is used for retrieving items.



Design Structure:
    
Player.java\
    PlayerInventory.java\
        ElementCollection.java\
            Item.java
    ElementCollection.java\
        Hero.java

HerosAndLegendsWorld.java\
    Market\
        MarketInventory\
            ElementCollection.java\
                Item.java
    CommonTile.java\
        ElementCollection.java\
            Monster.java
    UnreachableTile.java\

GamePlay:

    The game logic follows that of the instruction. The heros are allowed to 
        buy the same items multiple times regardless if they are consumables,
        and can sell them at half price. 
    
    When the hero dies from a battle, he/she is revived with half mana/health.
        Even when all heros die. Thus the game shall never end unless specifically
        selected

    When the hero is attacked, the defense value is calculated using the armor 
        defense reduction. This value is calculated everytime the hero is attacked
        even though the defense_reduction of the armor object will not change when
        displaying.