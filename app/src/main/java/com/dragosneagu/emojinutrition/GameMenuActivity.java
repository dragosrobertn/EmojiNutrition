package com.dragosneagu.emojinutrition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameMenuActivity extends AppCompatActivity {
    JSONObject saveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if a saved file exists already, if not, show startActivity
        if (!checkOrLoadSaveFile(GameMenuActivity.this)) {
            showStartActivity();
        }

        setContentView(R.layout.activity_emoji_action);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emoji_action, menu);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkOrLoadSaveFile(Context context) {
        boolean found = false;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;
        try {
            File fp = new File(context.getFilesDir(), "EmojiNutrition_SaveFile.json");
            in = new BufferedReader(new FileReader(fp));
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (FileNotFoundException e) {
//            Log.d("ID", e.getMessage());
        } catch (IOException e) {
            //Log.d("ID", e.getMessage());
        }

        if (stringBuilder.length() > 0)
        {
            found = true;
//            saveFile = (JSONObject)  ;
        }

        //Log.d("ID", stringBuilder.toString());

        return found;
    }

    private void showStartActivity(){
        Intent startActivity = new Intent(GameMenuActivity.this, StartActivity.class);
        startActivity(startActivity);
    }

}
