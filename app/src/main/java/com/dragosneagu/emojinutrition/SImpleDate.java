package com.dragosneagu.emojinutrition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public class SimpleDate{
    private DateFormat dateFormat;
    private Date date;

    public SimpleDate() {
        this.date = new Date();
    }

    public String getCurrentDate() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        return dateFormat.format(date);
    }

    public String getCurrentDateAndTime() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);
        return dateFormat.format(date);
    }


}
