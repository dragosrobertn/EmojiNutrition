package com.dragosneagu.emojinutrition;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Food {
    private String id;
    private String code;
    private String name;
    private String symbol;
    private String source;
    private String sourceDescription;
    private Map<String, String> calories = new HashMap<>();
    private Map<String, String> foodState = new HashMap<>();

    private Emoji emoji = new Emoji();

    public Food(String id, String code, String name, String source, String sourceDescription, Map<String, String> foodState, Map<String, String> calories) {
        this.code = code;
        this.id = id;
        this.name = name;
        this.symbol = emoji.getEmojiByUnicode(Integer.decode(code));
        this.source = source;
        this.sourceDescription = sourceDescription;
        this.foodState = foodState;
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = emoji.getEmojiByUnicode(Integer.parseInt(symbol));
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public AlertDialog newFoodUnlocked (Context context, Food unlockedFood) {
        AlertDialog.Builder f = new AlertDialog.Builder(context)
                .setTitle("You've unlocked a new ingredient!")
                .setMessage(String.format("%1$s\n\nYou have unlocked %2$s. %3$s", unlockedFood.getSymbol(), unlockedFood.getName(), unlockedFood.getSourceDescription()))
                .setPositiveButton("Wow, thanks!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        return f.show();
    }

}