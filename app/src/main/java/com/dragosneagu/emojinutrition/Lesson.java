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

    // #TODO
    private ArrayList<Food> unlocks = new ArrayList<>();

    public Lesson(int lessonID, String lessonTitle, String lessonContent, String lessonSource, String lessonImage, ArrayList<Food> unlocks) {
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

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public String getLessonImage() {
        return lessonImage;
    }

    public String getLessonSource() {
        return lessonSource;
    }

}
