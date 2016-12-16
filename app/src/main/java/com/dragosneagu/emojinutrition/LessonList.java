package com.dragosneagu.emojinutrition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class LessonList {
    private Map<String, Lesson> lessonList;

    public LessonList() {
        this.lessonList = new HashMap<>();
    }

    public void addLesson(String id, Lesson lesson){
        Lesson foodItem = lessonList.get(id);
        if(foodItem == null){
            lessonList.put(id, lesson);
        }
    }

    public Lesson getFromListByID(String id){
        return lessonList.get(id);
    }

    public boolean isEmpty() {
        return lessonList.isEmpty();
    }

}
