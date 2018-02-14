package com.davis.noah.facemaker;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Did not need to create this listener, had almost identical code in main
 * Had issues not holding color value though so though creating control would work
 * Didn't actually solve problem, but fixed it with singleton, and don't want to change it back
 *
 * This control listener takes in the three seekbars (RGB)
 * It determines which radio button is checked and changes seekbars accordingly to colors
 * Created by Noah on 2/14/2018.
 */

public class OnChecked implements RadioGroup.OnCheckedChangeListener {

    private ArrayList<SeekBar> seeks = new ArrayList<SeekBar>();

    public OnChecked(SeekBar red, SeekBar green, SeekBar blue) {
        //puts seekbars into arraylist to use in OnCheckedChanged
        seeks.addAll(Arrays.asList(red, green, blue));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //checks ID of radiobutton, each case is the ID of it
        //sets RGB seekbars to the given RGB values of that radiobuttton's color
        //0 = red, 1 = green, 2 = blue (in order of arraylist above)
        switch (checkedId) {
            case R.id.rbHair:
                seeks.get(0).setProgress(Globals.getInstance().rHair);
                seeks.get(1).setProgress(Globals.getInstance().gHair);
                seeks.get(2).setProgress(Globals.getInstance().bHair);
                break;
            case R.id.rbEyes:
                seeks.get(0).setProgress(Globals.getInstance().rEye);
                seeks.get(1).setProgress(Globals.getInstance().gEye);
                seeks.get(2).setProgress(Globals.getInstance().bEye);
                break;
            case R.id.rbSkin:
                seeks.get(0).setProgress(Globals.getInstance().rSkin);
                seeks.get(1).setProgress(Globals.getInstance().gSkin);
                seeks.get(2).setProgress(Globals.getInstance().bSkin);
                break;
        }
    }
}
