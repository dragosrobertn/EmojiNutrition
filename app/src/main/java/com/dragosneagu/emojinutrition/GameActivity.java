package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    Player player = new Player();
    Emoji emoji = new Emoji();
    StringBuilder playerStringBuilder;
    FoodInventory foodInventory = new FoodInventory();
    LessonList lessonList = new LessonList();

    Button lessonButton;
    Button mixButton;
    Button feedButton;

    TextView playerProfileGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buildLessonList(getApplicationContext());
        buildFoodInventory(getApplicationContext());

        lessonButton = (Button) findViewById(R.id.lessonButton);
        mixButton = (Button) findViewById(R.id.mixButton);
        feedButton = (Button) findViewById(R.id.feedButton);

        mixButton.setTextSize(30f);
        lessonButton.setTextSize(30f);
        feedButton.setTextSize(30f);
        lessonButton.setText(String.format(getString(R.string.let_s_learn_something), emoji.getEmojiByUnicode(0x1F4D6)));
        mixButton.setText(String.format(getString(R.string.game_mix_ingredients_text), emoji.getEmojiByUnicode(0x1F35B)));
        feedButton.setText(String.format(getString(R.string.game_feed_text), emoji.getEmojiByUnicode(0x1F374)));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Your character isn't hungry at the minute", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        checkPlayerExists();

        //Time to personalise the screen

        playerProfileGender = (TextView) findViewById(R.id.playerProfileGender);
        playerProfileGender.setTextSize(120f);
        reloadPlayerProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadPlayerProfile();
    }

    private void showStartActivity(){
        Intent startActivity = new Intent(GameActivity.this, StartActivity.class);
        startActivityForResult(startActivity, 1);
    }

    public void checkPlayerExists(){
        // Check if a saved file exists already, if not, show startActivity
        playerStringBuilder = new Player().checkPlayerFileExists(GameActivity.this);

        if (playerStringBuilder.length() > 0) {
            player = new Player().loadPlayerFile(playerStringBuilder, foodInventory, lessonList);
        }
        else {
            showStartActivity();
        }
    }

    private void reloadPlayerProfile(){
        setTitle(String.format("%1$s, %2$s years old", player.getName(), player.getAge()));
        if (player.getGender() == Gender.FEMALE) {
            playerProfileGender.setText(emoji.getEmojiByUnicode(0x1F64B));
        }
        else {
            playerProfileGender
                    .setText(emoji.getEmojiCombinationByUnicode(0x1F64B, 0x200D, 0x2642, 0xFE0F));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, getIntent());
        checkPlayerExists();
        reloadPlayerProfile();
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

    public void buildLessonList(Context context){
        try {
            JSONObject jsonL = new JSONObject(loadLocalJSONFile(context, R.raw.lessons));
            JSONObject jsonLessons = jsonL.getJSONObject("lessons");
            for(int i = 0; i < jsonLessons.length(); i++){
                JSONObject jsonLesson = jsonLessons.getJSONObject(String.format("%1$s",i+1));
                //TODO
                ArrayList<Food> unlockableFood = new ArrayList<>();
                Lesson lesson = new Lesson(
                        Integer.parseInt(jsonLesson.getString("id")),
                        jsonLesson.getString("title"),
                        jsonLesson.getString("content"),
                        jsonLesson.getString("source"),
                        jsonLesson.getString("photo_source"),
                        unlockableFood);
                lessonList.addLesson(jsonLesson.getString("id"), lesson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
