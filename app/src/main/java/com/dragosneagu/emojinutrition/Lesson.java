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
    private String lessonSource;
    private ArrayList<Food> unlocks = new ArrayList<>();

    public Lesson(int lessonID, String lessonTitle, String lessonContent, String lessonImage, String lessonSource, ArrayList<Food> unlocks) {
        this.lessonID = lessonID;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
        this.lessonImage = lessonImage;
        this.lessonSource = lessonSource;
        this.unlocks = unlocks;
    }

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

    public String getLessonSource() {
        return lessonSource;
    }

    public void setLessonSource(String lessonSource) {
        this.lessonSource = lessonSource;
    }

}
