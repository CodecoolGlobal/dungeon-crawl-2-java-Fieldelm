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

    public void useItem(Item item){
        if (items.containsKey(item)){
            System.out.println("You are using " + item.getTileName()+ " .");
        }
        else{
            System.out.println("You don't have" + item.getTileName()+ " to use.");
        }
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



    public void pickOutItem(Item item){
        if (items.containsKey(item)){
            if (items.get(item) >1){
                items.put(item, items.get(item) -1);
            }else {
                items.remove(item);
            }
            }
        else{
            System.out.println("No such item in inventory");
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
