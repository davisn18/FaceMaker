package com.davis.noah.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Noah Davis on 2/12/2018.
 */

public class Face extends SurfaceView {

    private Random rnd = new Random(); //generates random number
    private Paint paintSkin, paintEye, rndPaintHair, white; //create paint colors
    private SurfaceView sv;

    public Face(Context context) {
        super(context);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        generalInit();
    }

    /**
     * generalInit
     * Initialization stuff used by all ctors
     *
     * External Citation
     * Date: 12 February 2018
     * Problem: couldn't remember how to initialize surfaceview & drawing
     * Resource: MyArtwork.java link in moodle
     * Solution: used to set up class & onDraw method
     */
    private void generalInit() {
        setWillNotDraw(false);

        randomize();
        sv = (SurfaceView)findViewById(R.id.svFace);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //in order to use drawOval
    @Override
    public void onDraw(Canvas canvas)
    {
        //setting white to color of sclera
        white = new Paint();
        white.setColor(Color.WHITE);

        canvas.drawRect(300.0f, 300.0f, 1400.0f, 1200.f, rndPaintHair);
        canvas.drawCircle(900f, 1100f, 600f, paintSkin);
        canvas.drawOval(500f, 800f, 800f, 1100f, white);
        canvas.drawOval(1200f, 800f, 1350f, 1100f, white);
        canvas.drawCircle(650f, 950f, 100f, paintEye);
        canvas.drawCircle(1200f, 950f, 100f, paintEye);

    }

    private void randomize() {
        //loops through strings.xml skinColors & puts into a list of colors
        String[] skinText = getResources().getStringArray(R.array.skinColors);
        List<Integer> skinColorsList = new ArrayList<Integer>();
        for (int i = 0; i < skinText.length; i++) {
            int newColor = Color.parseColor(skinText[i]);
            skinColorsList.add(newColor);
        }
        //loops through strings.xml eyeColors & puts into a list of colors
        String[] eyeText = getResources().getStringArray(R.array.eyeColors);
        List<Integer> eyeColorsList = new ArrayList<Integer>();
        for (int i = 0; i < eyeText.length; i++) {
            int newColor = Color.parseColor(eyeText[i]);
            eyeColorsList.add(newColor);
        }

        paintSkin = new Paint();
        paintSkin.setColor(skinColorsList.get(rnd.nextInt(5))); //takes random color from list
        paintEye = new Paint();
        paintEye.setColor(eyeColorsList.get(rnd.nextInt(5))); //takes random color from list
        rndPaintHair = new Paint(); //sets colors for face features
        rndPaintHair.setARGB(0xFF, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
