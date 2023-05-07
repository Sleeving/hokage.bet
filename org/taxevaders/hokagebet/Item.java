package org.taxevaders.hokagebet;

public class Item {
    private String name;
    private Rarity rarity;
    private ItemType itemType;
    
    enum Rarity {
        MILSPEC, RESTRICTED,
        CLASSIFIED, COVERT,
        LEGENDARY
    }
    enum ItemType {
        GUN, KNIFE, GLOVES
    }
    public Item() {
        name = null;
        rarity = null;
    }
    public Item(String name, Rarity rarity, ItemType itemType) {
        this.name = name;
        this.rarity = rarity;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

}
