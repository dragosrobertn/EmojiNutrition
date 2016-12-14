package com.dragosneagu.emojinutrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MixActivity extends AppCompatActivity {
    Button ingredient1, ingredient2, ingredient3, ingredient4, resultIngredient;
    Emoji emoji = new Emoji();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix);
        setTitle("Mix Ingredients");

        ingredient1 = (Button) findViewById(R.id.ingredient1);
        ingredient2 = (Button) findViewById(R.id.ingredient2);
        ingredient3 = (Button) findViewById(R.id.ingredient3);
        ingredient4 = (Button) findViewById(R.id.ingredient4);
        resultIngredient = (Button) findViewById(R.id.resultIngredient);

        ingredient1.setTextSize(120f);
        ingredient2.setTextSize(120f);
        ingredient3.setTextSize(120f);
        ingredient4.setTextSize(120f);
        resultIngredient.setTextSize(120f);

        ingredient1.setText(emoji.getEmojiByUnicode(0x1F34D));
        ingredient2.setText(emoji.getEmojiByUnicode(0x1F34C));
        ingredient3.setText(emoji.getEmojiByUnicode(0x1F4A7));
        ingredient4.setText(emoji.getEmojiByUnicode(0x1F353));
        ingredient4.setText(emoji.getEmojiByUnicode(0x1F379));

    }
}
