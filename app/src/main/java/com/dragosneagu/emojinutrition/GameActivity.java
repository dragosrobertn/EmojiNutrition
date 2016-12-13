package com.dragosneagu.emojinutrition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    Player player = new Player();
    Food food = new Food();
    Emoji emoji = new Emoji();
    StringBuilder playerStringBuilder;
    ArrayList<Food> foodList = new ArrayList<>();
    FoodInventory foodInventory = new FoodInventory();
    LessonList lessonList = new LessonList();

    Button lessonButton;
    Button mixButton;

    TextView playerProfileGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lessonButton = (Button) findViewById(R.id.lessonButton);
        mixButton = (Button) findViewById(R.id.mixButton);

        mixButton.setTextSize(40f);
        lessonButton.setTextSize(40f);
        lessonButton.setText(String.format(getString(R.string.let_s_learn_something), emoji.getEmojiByUnicode(0x1F4D6)));
        mixButton.setText(String.format(getString(R.string.game_mix_ingredients_text), emoji.getEmojiByUnicode(0x1F35B)));




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        checkPlayerExists();

        //Time to personalise the screen

        playerProfileGender = (TextView) findViewById(R.id.playerProfileGender);
        playerProfileGender.setTextSize(120f);
        reloadPlayerProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadPlayerProfile();
    }

    private void showStartActivity(){
        Intent startActivity = new Intent(GameActivity.this, StartActivity.class);
        startActivityForResult(startActivity, 1);
    }

    public void checkPlayerExists(){
        // Check if a saved file exists already, if not, show startActivity
        playerStringBuilder = new Player().checkPlayerFileExists(GameActivity.this);

        if (playerStringBuilder.length() > 0) {
            player = new Player().loadPlayerFile(playerStringBuilder, foodInventory, lessonList);
        }
        else {
            showStartActivity();
        }
    }

    private void reloadPlayerProfile(){
        setTitle(String.format("%1$s, %2$s years old", player.getName(), player.getAge()));
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
