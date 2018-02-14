package com.davis.noah.facemaker;

import android.content.Context;

/**
 * Created by Noah on 2/14/2018.
 */

public class Globals {
    private static Globals ourInstance = null;

    //using these ints to get RGB values from the randomly selected colors
    public int rHair, gHair, bHair, rEye, gEye, bEye, rSkin, gSkin, bSkin;
    public int hairstyle; //used to default spinner with correct hairstyle on intial run
    public Face globalFace;

    public static synchronized Globals getInstance() {
        if (null == ourInstance) {
            ourInstance = new Globals();
        }
        return ourInstance;
    }
}
