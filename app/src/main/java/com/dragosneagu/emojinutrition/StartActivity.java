package com.dragosneagu.emojinutrition;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    TextView introTextView;
    Button avatarChoiceM, avatarChoiceF;

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
        // Time to play with emojis!!!
        introTextView.setText(String.format(getString(R.string.intro_text_view), emoji.getEmojiCombinationByUnicode(0x1F60A, 0, 0, 0)));
        avatarChoiceF.setTextSize(120f);
        avatarChoiceM.setTextSize(120f);
        avatarChoiceF.setText(emoji.getEmojiCombinationByUnicode(0x1F64B, 0, 0, 0));
        avatarChoiceM.setText(emoji.getEmojiCombinationByUnicode(0x1F64B, 0x200D, 0x2642, 0xFE0F));
    }

}
