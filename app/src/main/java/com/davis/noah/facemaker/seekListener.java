package com.davis.noah.facemaker;

import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 *
 * Created by Noah Davis on 2/12/2018.
 */

public class seekListener implements SeekBar.OnSeekBarChangeListener {

    private ArrayList<TextView> textViews = new ArrayList<TextView>();
    private ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
    private Face newFace;

    public seekListener(TextView textRed, TextView textGreen, TextView textBlue,
                        RadioButton hairRB, RadioButton eyesRB, RadioButton skinRB, Face face) {
        textViews.addAll(Arrays.asList(textRed, textGreen, textBlue));
        radioButtons.addAll(Arrays.asList(hairRB, eyesRB, skinRB));
        newFace = face;

    }

    /**
    External Citation
    Date: 12 February 2018
    Problem: wanted to create one seekbar listener for all three seekbars
    Resource:
    http:https://stackoverflow.com/questions/8719632/multiple-seekbars-listener
    Solution: I used the example code from this post.
    */
    @Override
    public void onProgressChanged (SeekBar colorSeekBars,int progress, boolean fromUser){
        switch (colorSeekBars.getId()) {

            case R.id.sbRed:
                textViews.get(0).setText("Red: " + progress);
                //checks if any radiobutton is checked, if so sets new value to global variable
                    if (radioButtons.get(0).isChecked())
                        Globals.getInstance().rHair = progress;
                    else if (radioButtons.get(1).isChecked())
                        Globals.getInstance().rEye = progress;
                    else if (radioButtons.get(2).isChecked())
                        Globals.getInstance().rSkin = progress;
                break;

            case R.id.sbGreen:
                textViews.get(1).setText("Green: " + progress);
                if (radioButtons.get(0).isChecked())
                    Globals.getInstance().gHair = progress;
                else if (radioButtons.get(1).isChecked())
                    Globals.getInstance().gEye = progress;
                else if (radioButtons.get(2).isChecked())
                    Globals.getInstance().gSkin = progress;
                break;

            case R.id.sbBlue:
                textViews.get(2).setText("Blue: " + progress);
                if (radioButtons.get(0).isChecked())
                    Globals.getInstance().bHair = progress;
                else if (radioButtons.get(1).isChecked())
                    Globals.getInstance().bEye = progress;
                else if (radioButtons.get(2).isChecked())
                    Globals.getInstance().bSkin = progress;
                break;
        }
        newFace.invalidate();
    }

    @Override
    public void onStartTrackingTouch (SeekBar seekBar){
        //do nothing
    }

    @Override
    public void onStopTrackingTouch (SeekBar seekBar){
        //do nothing
    }
}
