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

        TextView textRed = (TextView)findViewById(R.id.redTV);
        TextView textGreen = (TextView)findViewById(R.id.greenTV);
        TextView textBlue = (TextView)findViewById(R.id.blueTV);
        //register seekListener with the three TextViews
        seekListener listenSeek = new seekListener(textRed, textGreen, textBlue);
        //set each seekbar to run off the seekListener
        SeekBar seekRed = (SeekBar)findViewById(R.id.sbRed);
        SeekBar seekGreen = (SeekBar)findViewById(R.id.sbGreen);
        SeekBar seekBlue = (SeekBar)findViewById(R.id.sbBlue);
        seekRed.setOnSeekBarChangeListener(listenSeek);
        seekGreen.setOnSeekBarChangeListener(listenSeek);
        seekBlue.setOnSeekBarChangeListener(listenSeek);



    }

}
