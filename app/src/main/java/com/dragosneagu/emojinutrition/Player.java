package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.util.Log;
import android.util.StringBuilderPrinter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Player {
    private String name;
    private int age;
    private Gender gender;
    private SimpleDateFormat lastFed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);
    private ArrayList<Lesson> lessons;
    private ArrayList<Food> foodUnlocked;
    private JSONObject jsonPlayer;
    private JSONArray jsonFood = new JSONArray();
    private JSONArray jsonLessons = new JSONArray();

    // A few definitions for instantiating a Player object
    public Player(JSONObject jsonPlayer) {
        this.jsonPlayer = jsonPlayer;
        // call something else here to actually build something;
    }

    public Player() {}

    public Player(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public ArrayList<Food> getFoodUnlocked() {
        return foodUnlocked;
    }

    public void setFoodUnlocked(ArrayList<Food> foodUnlocked) {
        this.foodUnlocked = foodUnlocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SimpleDateFormat getLastFed() {
        return lastFed;
    }

    public void setLastFed(SimpleDateFormat lastFed) {
        this.lastFed = lastFed;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public StringBuilder checkPlayerFileExists(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;
        try {
            File fp = new File(context.getFilesDir(), "EMOJI_NUTRITION_SAVE_DATA.json");
            in = new BufferedReader(new FileReader(fp));
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            Log.d("ID FNF Exception", e.getMessage());
        } catch (IOException e) {
            Log.d("ID IO Exception", e.getMessage());
        }

        return stringBuilder;
    }

    public Player loadPlayerFile(StringBuilder stringBuilder){
        Player localPlayer;

        return localPlayer = new Player();
    }

    public boolean savePlayerFile(Player player, Context context) {
        String fileContents = createJSONPlayer(player);
        try {
            File fp = new File(context.getFilesDir(), "EMOJI_NUTRITION_SAVE_DATA.json");
            FileWriter out = new FileWriter(fp);
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            Log.d("Error","File error:" + e);
        }
        return true;
    }

    public String createJSONPlayer(Player player){
        jsonLessons = createJSONLessons(player.getLessons());
        jsonFood = createJSONFood(player.getFoodUnlocked());
        try{
            jsonPlayer.put("name", player.getName());
            jsonPlayer.put("age", player.getAge());
            jsonPlayer.put("gender", player.getGender().toString());
            jsonPlayer.put("lessons", jsonLessons);
            jsonPlayer.put("food", jsonFood);
            return jsonPlayer.toString(2);
        }
        catch (JSONException e){
            return e.getMessage();
        }
    }

    public JSONArray createJSONLessons(ArrayList<Lesson> lessons){
        JSONArray localJSONLessons = new JSONArray();
        for (Lesson lesson:lessons) {
            localJSONLessons.put(lesson.getLessonID());
        }
        return localJSONLessons;
    }

    public JSONArray createJSONFood(ArrayList<Food> foodUnlocked){
        JSONArray localJSONLessons = new JSONArray();
        for (Food food:foodUnlocked) {
            localJSONLessons.put(food.getCode());
        }
        return localJSONLessons;
    }


    public void loadPlayerFile(){}


}
