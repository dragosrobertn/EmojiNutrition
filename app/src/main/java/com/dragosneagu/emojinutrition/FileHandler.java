package com.dragosneagu.emojinutrition;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dragosneagu on 02/12/2016.
 */

public class FileHandler {
    private StringBuilder stringBuilder = new StringBuilder();
    private File file;

    public FileHandler(File fp) {
        this.file = fp;
    }

    public StringBuilder getStringBuilder(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            Log.d("ID FNF Exception", e.getMessage());
        } catch (IOException e) {
            Log.d("ID IO Exception", e.getMessage());
        }
        return stringBuilder;
    }

    public boolean writeToFileFromString(String fileContents){
        boolean success = false;
        try {
            FileWriter out = new FileWriter(file);
            out.write(fileContents);
            out.close();
            success = true;
        } catch (IOException e) {
            Log.d("Error","File error:" + e);
        }
        return success;
    }



}
