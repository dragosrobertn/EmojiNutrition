package com.dragosneagu.emojinutrition;

import static com.dragosneagu.emojinutrition.Constants.FIRST_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.SECOND_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.THIRD_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.FOURTH_COLUMN;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {
    FoodInventory foodInventory = new FoodInventory();
    public ArrayList<HashMap> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_activty);
        setTitle("Food Information");
        foodInventory = foodInventory.buildFoodInventory(getApplicationContext());

        ListView lv = (ListView) findViewById(R.id.listview);

        populateList();
        ListViewAdapter adapter = new ListViewAdapter(list, this, foodInventory);
        lv.setAdapter(adapter);
    }

    private void populateList() {
        list = new ArrayList<>();

        HashMap temp = new HashMap();
        int i = 4;
        for(Food f : foodInventory.getArrayList()){

            if (i % 4 == 0) temp.put(FIRST_COLUMN, f.getId());
            if (i % 4 == 1) temp.put(SECOND_COLUMN, f.getId());
            if (i % 4 == 2) temp.put(THIRD_COLUMN, f.getId());
            if (i % 4 == 3) temp.put(FOURTH_COLUMN, f.getId());

            if (i % 4 == 3) {
                list.add(temp);
                temp = new HashMap();
            }
            i++;
        }

    }

    // This is called when an element is pressed so we can show info about that food item

    public void showFoodInfo(View v){

        Food fi = foodInventory.getFromInventoryByCode(v.getTag().toString());
        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
        alertDialog.setTitle(String.format("%1$s %2$s", fi.getName(), fi.getSymbol()));
        alertDialog.setMessage(String.format(
            "%1$s\n" +
            "%2$s\n\n" +
            "A regular sized portion has %3$s calories\n" +
            "Mass: %4$s",
            fi.getType(),
            fi.getSourceDescription(),
            fi.getCaloriesBySize("medium"),
            fi.getFoodState("mass")));

        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}
