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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Noah Davis on 2/12/2018.
 */

public class MainActivity extends AppCompatActivity {

    //used to set progress of seekbars when user changes radio button
    private SeekBar redSB, greenSB, blueSB;
    private RadioGroup rg;
    private Face face; //for calling methods from Face class within main
    private int check = 0; //used to not call code when first time spinner is pressed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        face = new Face(getApplicationContext()); //instantiate Face
        Globals.getInstance().globalFace = face;

        TextView textRed = (TextView)findViewById(R.id.redTV);
        TextView textGreen = (TextView)findViewById(R.id.greenTV);
        TextView textBlue = (TextView)findViewById(R.id.blueTV);

        //setting variable for radio buttons
        final RadioButton rbHair = (RadioButton)findViewById(R.id.rbHair);
        final RadioButton rbEyes = (RadioButton)findViewById(R.id.rbEyes);
        final RadioButton rbSkin = (RadioButton)findViewById(R.id.rbSkin);

        //register seekListener with the three TextViews & RadioButtons (needed for color change to face feature)
        seekListener listenSeek = new seekListener(textRed, textGreen, textBlue, rbHair, rbEyes, rbSkin);
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

        //spinner selection control
        //gets or sets the spinner selection and uses it to change spinner selection or face's hairstyle
        final Spinner spHairstyle = (Spinner)findViewById(R.id.hairStyleSpin);
        spHairstyle.setSelection(Globals.getInstance().hairstyle);
        spHairstyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //if statement in place so it doesn't run when user just clicks on the spinner
                //waits for the second selection which would be the new hairstyle the user selected
                if (++check > 1) {
                    Globals.getInstance().hairstyle = position;
                    face.invalidate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

        //register Random Face button with onClickListener
        Button randomButton = (Button)findViewById(R.id.bnRandomFace);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                face.randomize(); //calls randomize function to get new colors and hairstyle

                //sets what selection the spinner should be showing based off new hairstyle
                //goes through and sets seekbars to match new colors based on radio button checked
                spHairstyle.setSelection(Globals.getInstance().hairstyle);
                if (rbHair.isChecked() == true) {
                    redSB.setProgress(Globals.getInstance().rHair);
                    greenSB.setProgress(Globals.getInstance().gHair);
                    blueSB.setProgress(Globals.getInstance().bHair);
                }
                else if (rbEyes.isChecked() == true) {
                    redSB.setProgress(Globals.getInstance().rEye);
                    greenSB.setProgress(Globals.getInstance().gEye);
                    blueSB.setProgress(Globals.getInstance().bEye);
                }
                else if (rbSkin.isChecked() == true) {
                    redSB.setProgress(Globals.getInstance().rSkin);
                    greenSB.setProgress(Globals.getInstance().gSkin);
                    blueSB.setProgress(Globals.getInstance().bSkin);
                }
            }
        });
    }
}
