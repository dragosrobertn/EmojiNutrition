package com.dragosneagu.emojinutrition;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Food {
    private String name;
    private String source;
    private String sourceDescription;
    //private Map<Enumeration state, boolean raw> foodState;


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

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public AlertDialog foodUnlocked (Context context, Food unlockedFood) {
        AlertDialog.Builder f = new AlertDialog.Builder(context)
                .setTitle("You've unlocked a new ingredient!")
                .setMessage(String.format("You have unlocked %1$s. %2$s", unlockedFood.getName(), unlockedFood.getSourceDescription()))
                .setPositiveButton("Wow, thanks!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        return f.show();
    }
}