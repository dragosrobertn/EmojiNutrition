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

    public Set<String> getListOfLessons(){
        return lessonList.keySet();
    }

    public Map<String, Lesson> getLessonList(){
        return lessonList;
    }

    public Lesson getFromListByID(String id){
        return lessonList.get(id);
    }

    public boolean isEmpty() {
        return lessonList.isEmpty();
    }

    public ArrayList<Lesson> getListAsArray() {
        ArrayList<Lesson> arrayList = new ArrayList<>();

        for(Map.Entry<String, Lesson> lesson : lessonList.entrySet()){
            arrayList.add(lesson.getValue());
        }
        return arrayList;
    }
}
