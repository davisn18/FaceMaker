package com.davis.noah.facemaker;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Noah Davis on 2/12/2018.
 */

public class MainActivity extends AppCompatActivity {

    //used to set progress of seekbars when user changes radio button
    private SeekBar redSB, greenSB, blueSB;
    private RadioGroup rg;

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
        redSB = (SeekBar)findViewById(R.id.sbRed);
        greenSB = (SeekBar)findViewById(R.id.sbGreen);
        blueSB = (SeekBar)findViewById(R.id.sbBlue);
        redSB.setOnSeekBarChangeListener(listenSeek);
        greenSB.setOnSeekBarChangeListener(listenSeek);
        blueSB.setOnSeekBarChangeListener(listenSeek);

        //controller used for radio buttons and changing seekbar progress with RGB values of color of the face feature
        OnChecked checker = new OnChecked(redSB, greenSB, blueSB);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(checker);

        //register Random Face button with onClickListener
        Button randomButton = (Button)findViewById(R.id.bnRandomFace);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
