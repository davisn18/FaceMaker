package com.davis.noah.facemaker;

import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

import static com.davis.noah.facemaker.R.id.blueTV;
import static com.davis.noah.facemaker.R.id.greenTV;
import static com.davis.noah.facemaker.R.id.redTV;

/**
 * Created by Noah Davis on 2/12/2018.
 */

public class seekListener implements SeekBar.OnSeekBarChangeListener {

    private ArrayList<TextView> textViews = new ArrayList<TextView>();

    public seekListener(TextView textRed, TextView textGreen, TextView textBlue) {
        textViews.addAll(Arrays.asList(textRed, textGreen, textBlue));
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
                break;

            case R.id.sbGreen:
                textViews.get(1).setText("Green: " + progress);
                break;

            case R.id.sbBlue:
                textViews.get(2).setText("Blue: " + progress);
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
}
