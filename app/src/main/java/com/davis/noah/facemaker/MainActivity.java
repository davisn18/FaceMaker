package com.davis.noah.facemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] hairStyles =
            {"Mohwak", "Bald", "Flat Top"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private SeekBar.OnSeekBarChangeListener seekListener = new SeekBar.OnSeekBarChangeListener()
    {
        TextView textRed = (TextView)findViewById(R.id.redTV);
        TextView textGreen = (TextView)findViewById(R.id.greenTV);
        TextView textBlue = (TextView)findViewById(R.id.blueTV);

        @Override
        public void onProgressChanged (SeekBar colorSeekBars,int progress, boolean fromUser){
        switch (colorSeekBars.getId()) {

            case R.id.sbRed:
                textRed.setText("Red: " + progress);
                break;

            case R.id.sbGreen:
                textGreen.setText("Green: " + progress);
                break;

            case R.id.sbBlue:
                textBlue.setText("Blue: " + progress);
                break;
        }
    }

        @Override
        public void onStartTrackingTouch (SeekBar seekBar){
        //do nothing
    }

        @Override
        public void onStopTrackingTouch (SeekBar seekBar){
        //do nothing
    }
    };
}
