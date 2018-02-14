package com.davis.noah.facemaker;

/**
 * Created by Noah on 2/14/2018.
 */

public class Colors {
    private static Colors ourInstance = null;

    //using these ints to get RGB values from the randomly selected colors
    public int rHair, gHair, bHair, rEye, gEye, bEye, rSkin, gSkin, bSkin;

    public static synchronized Colors getInstance() {
        if (null == ourInstance) {
            ourInstance = new Colors();
        }
        return ourInstance;
    }
}
