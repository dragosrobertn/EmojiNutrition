package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public ArrayList<Food> getArrayList(){
        ArrayList<Food> f = new ArrayList<>();
        for(String s : this.getFoodList()){
            f.add(this.getFromInventoryByCode(s));
        }
        return f;
    }

    public Food getFromInventoryByCode(String code){
        return inventory.get(code);
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public FoodInventory build(Context context){
        FoodInventory foodInventory =  new FoodInventory();
        Map<String, String> calories = new HashMap<>();
        Map<String, String> foodState = new HashMap<>();

        try {
            JSONObject jsonF = new JSONObject(loadLocalJSONFile(context, R.raw.rawfood));
            JSONObject jsonFood = jsonF.getJSONObject("food");
            for(int i = 0; i < jsonFood.length(); i++){
                JSONObject jfi = jsonFood.getJSONObject(String.format("%1$s",i));

                JSONObject jfiCalories = jfi.getJSONObject("calories");
                JSONObject jfiFoodState = jfi.getJSONObject("state");

                calories.put("small", jfiCalories.getString("small"));
                calories.put("medium", jfiCalories.getString("medium"));
                calories.put("large", jfiCalories.getString("large"));

                foodState.put("raw", jfiFoodState.getString("raw"));
                foodState.put("mass", jfiFoodState.getString("mass"));

                Food food = new Food(
                        jfi.getString("id"),
                        jfi.getString("code"),
                        jfi.getString("name"),
                        jfi.getString("source"),
                        jfi.getString("source_desc"),
                        foodState,
                        calories
                );
                foodInventory.addFood(jfi.getString("id"), food);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foodInventory;
    }

    public String loadLocalJSONFile(Context context, int fileID) {
        String json;
        try {
            InputStream is = context.getResources().openRawResource(fileID);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
