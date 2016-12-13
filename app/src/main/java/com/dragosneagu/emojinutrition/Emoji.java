package com.dragosneagu.emojinutrition;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class Emoji {

    public String getEmojiByUnicode(int unicode) {

        return new String(Character.toChars(unicode));
    }

    public String getEmojiCombinationByUnicode(int unicode1, int unicode2, int unicode3, int unicode4) {
        String full = new String();

        if (!(unicode1 == 0))
            full += new String(Character.toChars(unicode1));

        if (!(unicode2 == 0))
            full += new String(Character.toChars(unicode2));

        if (!(unicode3 == 0))
            full += new String(Character.toChars(unicode3));

        if (!(unicode4 == 0))
            full += new String(Character.toChars(unicode4));

        return full;

    }
}
