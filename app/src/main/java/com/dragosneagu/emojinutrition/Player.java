package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Player {
    private String name;
    private int age;
    private Gender gender;
    private SimpleDate lastFed = new SimpleDate();
    private ArrayList<Lesson> lessons;
    private ArrayList<Food> foodUnlocked;
    private JSONObject jsonPlayer = new JSONObject();
    private JSONArray jsonFood = new JSONArray();
    private JSONArray jsonLessons = new JSONArray();

    public Player() {
        this.name = new String();
        this.gender = Gender.OTHER;
        this.age = 0;
        this.lastFed = new SimpleDate();
        this.lessons = new ArrayList<>();
        this.foodUnlocked = new ArrayList<>();
    }

    public ArrayList<Food> getFoodUnlocked() {
        return foodUnlocked;
    }

    public void addUnlockedFood(Food foodUnlocked) {

        this.foodUnlocked.add(foodUnlocked);
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

    public SimpleDate getLastFed() {
        return lastFed;
    }

    public void setLastFed(SimpleDate lastFed) {
        this.lastFed = lastFed;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
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

    public Player loadPlayerFile(StringBuilder stringBuilder, FoodInventory foodInventory, LessonList lessonList){
        Player localPlayer = new Player();
        try {
            jsonPlayer = new JSONObject(stringBuilder.toString());
            localPlayer.setAge(Integer.parseInt(jsonPlayer.getString("age")));
            localPlayer.setName(jsonPlayer.getString("name"));
            localPlayer.setLastFed(new SimpleDate(jsonPlayer.getString("last_fed")));
            String a = jsonPlayer.getString("gender");
            if (jsonPlayer.getString("gender").equals("MALE")){
                localPlayer.setGender(Gender.MALE);
            }
            else {
                localPlayer.setGender(Gender.FEMALE);
            }
            jsonFood = new JSONArray(jsonPlayer.getJSONArray("food"));
            jsonLessons = new JSONArray(jsonPlayer.getJSONArray("lessons"));

            for (int i = 0; i < jsonFood.length(); i++) {
            localPlayer.addUnlockedFood(foodInventory.getFromInventoryByCode(jsonFood.getJSONObject(i).toString()));
            }

            for (int i = 0; i < jsonFood.length(); i++) {
                localPlayer.addLesson(lessonList.getFromListByID(jsonLessons.getJSONObject(i).toString()));
            }
        }
        catch (JSONException e){
            e.getMessage();
        }

        return localPlayer;
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
            jsonPlayer.put("last_fed", new SimpleDate().getCurrentDateAndTime());
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

}
