package com.dragosneagu.emojinutrition;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    TextView introTextView;
    Emoji emoji = new Emoji();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // We set this as a string.
        setTitle(getString(R.string.start_title));
        introTextView = (TextView) findViewById(R.id.introTextView);
        introTextView.setText(String.format(getString(R.string.intro_text_view), emoji.getEmojiByUnicode((int)0x1F60A)));
    }

}
