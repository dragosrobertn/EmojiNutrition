package com.dragosneagu.emojinutrition;

import static com.dragosneagu.emojinutrition.Constants.FIRST_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.SECOND_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.THIRD_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.FOURTH_COLUMN;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {
    FoodInventory foodInventory = new FoodInventory();
    public ArrayList<HashMap> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_activty);
        setTitle("Feed your Character");
        buildFoodInventory(getApplicationContext());

        ListView lv = (ListView) findViewById(R.id.listview);

        populateList();
        ListViewAdapter adapter = new ListViewAdapter(list, this);
        lv.setAdapter(adapter);

    }

    public void buildFoodInventory(Context context){
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
        foodInventory.isEmpty();
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
    private void populateList() {
        list = new ArrayList<>();

        HashMap temp = new HashMap();
        int i = 4;
        for(Food f : foodInventory.getArrayList()){

            if (i % 4 == 0) temp.put(FIRST_COLUMN, f.getSymbol());
            if (i % 4 == 1) temp.put(SECOND_COLUMN, f.getSymbol());
            if (i % 4 == 2) temp.put(THIRD_COLUMN, f.getSymbol());
            if (i % 4 == 3) temp.put(FOURTH_COLUMN, f.getSymbol());

            if (i % 4 == 3) {
                list.add(temp);
                temp.clear();
            }
            i++;
        }

    }
}
