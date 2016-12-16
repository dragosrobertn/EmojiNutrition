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

import java.util.Date;

public class GameActivity extends AppCompatActivity {
    Player player = new Player();
    Emoji emoji = new Emoji();
    StringBuilder playerStringBuilder;
    FoodInventory foodInventory = new FoodInventory();
    LessonList lessonList = new LessonList();
    Button lessonButton, mixButton, feedButton;
    TextView playerProfileGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lessonButton = (Button) findViewById(R.id.lessonButton);
        mixButton = (Button) findViewById(R.id.mixButton);
        feedButton = (Button) findViewById(R.id.feedButton);

        mixButton.setTextSize(30f);
        lessonButton.setTextSize(30f);
        feedButton.setTextSize(30f);
        lessonButton.setText(String.format(getString(R.string.let_s_learn_something), emoji.getEmojiByUnicode(0x1F4D6)));
        mixButton.setText(String.format(getString(R.string.game_mix_ingredients_text), emoji.getEmojiByUnicode(0x1F35B)));
        feedButton.setText(String.format(getString(R.string.game_feed_text), emoji.getEmojiByUnicode(0x1F374)));

        lessonButton.setOnClickListener(lessonListener);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message;
                if (compareTimes(player.getLastFed().getDate(), new Date()) > 30) {
                    message = "It's time to feed your character!";
                }
                else
                {
                    message =  "Your character isn't hungry at the minute";
                }
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        feedButton.setOnClickListener(feedListener);
        mixButton.setOnClickListener(mixListener);

        checkPlayerExists();

        //Time to personalise the screen

        playerProfileGender = (TextView) findViewById(R.id.playerProfileGender);
        playerProfileGender.setTextSize(120f);
        reloadPlayerProfile();
    }

    View.OnClickListener lessonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent lessonActivity = new Intent(GameActivity.this, LessonsActivity.class);
        startActivityForResult(lessonActivity, 1);
        }
    };

    View.OnClickListener feedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent lessonActivity = new Intent(GameActivity.this, FeedActivity.class);
            startActivityForResult(lessonActivity, 1);
        }
    };

    View.OnClickListener mixListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent lessonActivity = new Intent(GameActivity.this, MixActivity.class);
            startActivityForResult(lessonActivity, 1);
        }
    };

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

    // This is not my code, and not because I don't know how to compare dates, but because I wasn't
    // sure of what format the date objects return (epoch in milliseconds or a date format etc)
    // http://stackoverflow.com/questions/7676149/compare-only-the-time-portion-of-two-dates-ignoring-the-date-part
    public int compareTimes(Date d1, Date d2)
    {
        int t1, t2;

        t1 = (int) (d1.getTime() % (24*60*60*1000L));
        t2 = (int) (d2.getTime() % (24*60*60*1000L));
        return ((t2-t1) / 24 / 60 / 60);
    }

}
