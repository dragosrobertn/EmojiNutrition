package com.dragosneagu.emojinutrition;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Player {
    private String name;
    private int age;
    private Gender gender;
    private SimpleDateFormat lastFed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);
    private ArrayList<Lessons> lessons;
    private ArrayList<Food> foodUnlocked;
    private JSONObject jsonPlayer;
    private JSONArray jsonFood = new JSONArray();

    // A few definitions for instantiating a Player object
    public Player(JSONObject jsonPlayer) {
        this.jsonPlayer = jsonPlayer;
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

    public ArrayList<Lessons> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lessons> lessons) {
        this.lessons = lessons;
    }

    public boolean savePlayerFile(Player player) {

        return true;
    }

    public void loadPlayerFile(){}


}
