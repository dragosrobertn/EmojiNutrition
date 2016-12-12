package com.dragosneagu.emojinutrition;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Emoji {

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
