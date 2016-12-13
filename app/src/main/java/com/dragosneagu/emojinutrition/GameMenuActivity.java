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
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameMenuActivity extends AppCompatActivity {
    Player player = new Player();
    Food food = new Food();
    Emoji emoji = new Emoji();
    StringBuilder playerStringBuilder;
    ArrayList<Food> foodList = new ArrayList<>();
    FoodInventory foodInventory = new FoodInventory();
    LessonList lessonList = new LessonList();

    TextView playerProfileGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Build Food Inventory!


        checkPlayerExists();

        setContentView(R.layout.activity_emoji_action);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Time to personalise the screen

        playerProfileGender = (TextView) findViewById(R.id.playerProfileGender);
        playerProfileGender.setTextSize(120f);
        reloadPlayerProfile();

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
    protected void onResume() {
        super.onResume();
        reloadPlayerProfile();
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

    private void showStartActivity(){
        Intent startActivity = new Intent(GameMenuActivity.this, StartActivity.class);
        startActivityForResult(startActivity, 1);
    }

    public void checkPlayerExists(){
        // Check if a saved file exists already, if not, show startActivity
        playerStringBuilder = new Player().checkPlayerFileExists(GameMenuActivity.this);

        if (playerStringBuilder.length() > 0) {
            player = new Player().loadPlayerFile(playerStringBuilder, foodInventory, lessonList);
        }
        else {
            showStartActivity();
        }
    }

    private void reloadPlayerProfile(){
        setTitle(String.format("%1$s, %2$s", player.getName(), player.getAge()));
        if (player.getGender() == Gender.FEMALE) {
            playerProfileGender.setText(emoji.getEmojiByUnicode(0x1F64B));
        }
        else {
            playerProfileGender
                    .setText(emoji.getEmojiCombinationByUnicode(0x1F64B, 0x200D, 0x2642, 0xFE0F));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, getIntent());
        checkPlayerExists();
        reloadPlayerProfile();
    }

}
