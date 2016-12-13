package com.dragosneagu.emojinutrition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class FoodInventory {
    private Map<String, Food> inventory;

    public FoodInventory() {
        this.inventory = new HashMap<>();
    }

    public void addFood(String code, Food food){
        Food foodItem = inventory.get(code);
        if(foodItem == null){
            inventory.put(code, food);
        }
    }

    public Set<String> getFoodList(){
        return inventory.keySet();
    }

    public Map<String, Food> getFoodInventory(){
        return inventory;
    }

    public Food getFromInventoryByCode(String code){
        return inventory.get(code);
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }
}