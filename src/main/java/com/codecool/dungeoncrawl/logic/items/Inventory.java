package com.codecool.dungeoncrawl.logic.items;

import java.util.HashMap;
import java.util.Objects;

public class Inventory {
    private HashMap <Item, Integer> items;

    public Inventory(){
        this.items = new HashMap<>();
    }

    public void addItem(Item item){
        if (items.containsKey(item)) {
            items.put(item, items.get(item) +1);
        }
        items.put(item, 1);
    }


public boolean hasMagicWand(){
    for (Item key : items.keySet()) {
        if (Objects.equals(key.getTileName(), "magicWand")) return true;
    }
    return false;
}
    public boolean hasMagicKey(){
        for (Item key : items.keySet()) {
            if (Objects.equals(key.getTileName(), "magicKey")) return true;
        }
        return false;
    }

    public void useItem(String itemName){
        for (Item key : items.keySet()){
            if(Objects.equals(key.getTileName(), itemName)){
                if(items.get(key) > 1){
                    items.put(key, items.get(key) -1);
                }else{
                    items.remove(key);
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder inventoryString = new StringBuilder("Inventory items:\n");

        items.forEach((item, amount) -> {
            inventoryString.append(item.getTileName() +": "+ amount + "\n");
        });
        return (items.size() >0 ? inventoryString.toString(): "No items in inventory");
    }


}
