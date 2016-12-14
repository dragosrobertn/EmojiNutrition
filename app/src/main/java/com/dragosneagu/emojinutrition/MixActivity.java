package com.dragosneagu.emojinutrition;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.Toast;

public class MixActivity extends AppCompatActivity {
    Button ingredient1, ingredient2, ingredient3, ingredient4, resultIngredient;
    Emoji emoji = new Emoji();
    SensorManager sensorManager;
    Sensor accelerometer;
    private float last_x=-1.0f, last_y=-1.0f, last_z=-1.0f;
    private long lastUpdate;
    float x = Integer.MAX_VALUE;
    float y = Integer.MAX_VALUE;
    float z = Integer.MAX_VALUE;

    private static final int SHAKE_THRESHOLD = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix);
        setTitle("Mix Ingredients");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener listener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                // We set the x,y,z first thing when we get them;

                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    long curTime = System.currentTimeMillis();
                    // only allow one update every 100ms.
                    if ((curTime - lastUpdate) > 100) {
                        long diffTime = (curTime - lastUpdate);
                        lastUpdate = curTime;

                        x = event.values[0];
                        y = event.values[1];
                        z = event.values[2];

                        float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                        if (speed > SHAKE_THRESHOLD) {
                            Toast.makeText(getApplicationContext(), "Wow you obtained a new ingredient!", Toast.LENGTH_SHORT).show();
                            combineIngredients();
                        }
                        last_x = x;
                        last_y = y;
                        last_z = z;
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub

            }

        };

        sensorManager.registerListener(
                listener,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);


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
        resultIngredient.setText(emoji.getEmojiByUnicode(0x1F379));

    }

    public void combineIngredients(){
        ingredient1.setVisibility(View.INVISIBLE);
        ingredient2.setVisibility(View.INVISIBLE);
        ingredient3.setVisibility(View.INVISIBLE);
        ingredient4.setVisibility(View.INVISIBLE);
        resultIngredient.setVisibility(View.VISIBLE);
    }
}
