package com.dragosneagu.emojinutrition;

import java.util.ArrayList;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Lesson {
    private int lessonID;
    private String lessonTitle;
    private String lessonContent;
    private String lessonImage;
    private ArrayList<Food> unlocks;

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }

    public String getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(String lessonImage) {
        this.lessonImage = lessonImage;
    }

    public ArrayList<Food> getUnlocks() {
        return unlocks;
    }

    public void setUnlocks(ArrayList<Food> unlocks) {
        this.unlocks = unlocks;
    }
}
