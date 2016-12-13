package com.dragosneagu.emojinutrition;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.EventListener;

public class StartActivity extends AppCompatActivity {
    TextView introTextView;
    EditText playerName, playerAge;
    Button avatarChoiceM, avatarChoiceF, saveButton;
    Gender chosenGender;
    Player player = new Player();
    Emoji emoji = new Emoji();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // We set this as a string.
        setTitle(getString(R.string.start_title));
        introTextView = (TextView) findViewById(R.id.introTextView);
        avatarChoiceF = (Button) findViewById(R.id.avatarChoiceF);
        avatarChoiceM = (Button) findViewById(R.id.avatarChoiceM);
        playerName = (EditText) findViewById(R.id.playerNameText);
        playerAge = (EditText) findViewById(R.id.playerAgeText);
        saveButton = (Button) findViewById(R.id.saveButton);

        // Time to play with emojis!!!
        introTextView.setText(String.format(getString(R.string.intro_text_view), emoji.getEmojiCombinationByUnicode(0x1F60A, 0, 0, 0)));
        avatarChoiceF.setTextSize(120f);
        avatarChoiceM.setTextSize(120f);
        avatarChoiceF.setText(emoji.getEmojiByUnicode(0x1F64B));
        avatarChoiceM.setText(emoji.getEmojiCombinationByUnicode(0x1F64B, 0x200D, 0x2642, 0xFE0F));

        // Let's set the listeners
        avatarChoiceF.setOnClickListener(chosenGenderListener);
        avatarChoiceM.setOnClickListener(chosenGenderListener);
        saveButton.setOnClickListener(registerPlayer);

    }

    View.OnClickListener chosenGenderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.avatarChoiceF:
                    avatarChoiceF.setBackgroundColor(Color.LTGRAY);
                    avatarChoiceM.setBackgroundColor(Color.TRANSPARENT);
                    chosenGender = Gender.FEMALE;
                    break;
                case R.id.avatarChoiceM:
                    avatarChoiceM.setBackgroundColor(Color.LTGRAY);
                    avatarChoiceF.setBackgroundColor(Color.TRANSPARENT);
                    chosenGender = Gender.MALE;
            }
        }
    };

    View.OnClickListener registerPlayer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Let's do some validation
            boolean registrationSuccess = false;

            if (!(chosenGender == Gender.FEMALE || chosenGender == Gender.MALE)){
                new AlertDialog.Builder(view.getContext()).setTitle("Gender not chosen")
                        .setMessage("Please choose a gender.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        }).show();
            }
            else if ((playerName.getText().length() == 0) || (playerAge.getText().length() == 0)){
                new AlertDialog.Builder(view.getContext()).setTitle("Fields incomplete")
                        .setMessage("Please fill in all fields, including name and age.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        }).show();
            }
            else {
                try {
                    player.setName(playerName.getText().toString());
                    player.setAge(Integer.parseInt(playerAge.getText().toString()));
                    player.setGender(chosenGender);
                    registrationSuccess = player.savePlayerFile(player, view.getContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (registrationSuccess) registrationDone();
        }
    };

    public void registrationDone(){
        StartActivity.this.finish();
    }

}
