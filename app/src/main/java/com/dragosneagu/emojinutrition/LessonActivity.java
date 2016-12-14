package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity implements
        LessonListFragment.OnListFragmentInteractionListener,
        LessonFragment.OnFragmentInteractionListener {

    LessonListFragment lessonListFragment;
    LessonFragment lessonFragment;
    FragmentManager fragmentManager;

    LessonList lessonList = new LessonList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buildLessonList(getApplicationContext());

        setTitle("Lessons List");
        lessonListFragment = new LessonListFragment();
        lessonFragment = new LessonFragment();

        lessonListFragment.setLessonList(lessonList.getListAsArray());

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_lesson, lessonListFragment).commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Lesson lesson) {
        fragmentManager.beginTransaction().replace(R.id.content_lesson, lessonFragment).commit();
    }

    public void buildLessonList(Context context){
        try {
            JSONObject jsonL = new JSONObject(loadLocalJSONFile(context, R.raw.lessons));
            JSONObject jsonLessons = jsonL.getJSONObject("lessons");
            for(int i = 0; i < jsonLessons.length(); i++){
                JSONObject jsonLesson = jsonLessons.getJSONObject(String.format("%1$s",i+1));
                //TODO
                ArrayList<Food> unlockableFood = new ArrayList<>();
                Lesson lesson = new Lesson(
                        Integer.parseInt(jsonLesson.getString("id")),
                        jsonLesson.getString("title"),
                        jsonLesson.getString("content"),
                        jsonLesson.getString("source"),
                        jsonLesson.getString("photo_source"),
                        unlockableFood);
                lessonList.addLesson(jsonLesson.getString("id"), lesson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadLocalJSONFile(Context context, int fileID) {
        String json;
        try {
            InputStream is = context.getResources().openRawResource(fileID);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void showLessonByID(String id){
        lessonFragment.setLesson(lessonList.getFromListByID(id));
        fragmentManager.beginTransaction().replace(R.id.content_lesson, lessonFragment).commit();
    }

}
