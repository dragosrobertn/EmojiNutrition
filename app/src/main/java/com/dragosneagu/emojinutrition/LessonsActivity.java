package com.dragosneagu.emojinutrition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LessonsActivity extends AppCompatActivity {
    LessonList lessonList = new LessonList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_lessons);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        buildLessonList(getApplicationContext());

        String[] sections = new String[7];
        for(int i = 0; i <= 6; i++){
            sections[i] = lessonList.getFromListByID(String.format("%1$s",i+1)).getLessonTitle();
        }

        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                sections));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1, lessonList))
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo_lessons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static LessonList LESSON_LIST = new LessonList();

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, LessonList lessons) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            LESSON_LIST = lessons;
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_demo_lessons, container, false);
            TextView lessonTitle = (TextView) rootView.findViewById(R.id.lessonTitle);
            TextView lessonContent = (TextView) rootView.findViewById(R.id.lessonContent);
            TextView lessonSource = (TextView) rootView.findViewById(R.id.lessonSource);
            ImageView lessonPhoto = (ImageView) rootView.findViewById(R.id.lessonPhoto);
            Bitmap bmp;

            lessonTitle.setText(LESSON_LIST.getFromListByID(String.format("%1$s", getArguments().getInt(ARG_SECTION_NUMBER))).getLessonTitle());
            lessonContent.setText(LESSON_LIST.getFromListByID(String.format("%1$s", getArguments().getInt(ARG_SECTION_NUMBER))).getLessonContent());
            lessonSource.setText(LESSON_LIST.getFromListByID(String.format("%1$s", getArguments().getInt(ARG_SECTION_NUMBER))).getLessonSource());

            URL url = null;
            try {
                url = new URL(LESSON_LIST.getFromListByID(String.format("%1$s", getArguments().getInt(ARG_SECTION_NUMBER))).getLessonImage());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                lessonPhoto.setImageBitmap(bmp);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return rootView;
        }
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
}
