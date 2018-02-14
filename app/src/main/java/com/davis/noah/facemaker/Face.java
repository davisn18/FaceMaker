package com.davis.noah.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Noah Davis on 2/12/2018.
 */

public class Face extends SurfaceView {

    private Random rnd = new Random(); //generates random number
    private Paint paintSkin, paintEye, rndPaintHair, white, black; //create paint colors
    private int redSkin, greenSkin, blueSkin, redEye, greenEye, blueEye, redHair, greenHair, blueHair;
    private String hairStyleString;

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
        MainActivity main = new MainActivity();

        //main.rHair = redHair; main.gHair = greenHair; main.bHair = blueHair;
        //main.rEye = redEye; main.gEye = greenEye; main.bEye = blueEye;
        //main.rSkin = redSkin; main.gSkin = greenSkin; main.bSkin = blueSkin;
        sv = (SurfaceView)findViewById(R.id.svFace);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //in order to use drawOval
    @Override
    public void onDraw(Canvas canvas)
    {
        //setting white to color of sclera
        white = new Paint();
        white.setColor(Color.WHITE);
        //setting black to color of pupil
        black = new Paint();
        black.setColor(Color.BLACK);

        drawHair(canvas, hairStyleString);

        canvas.drawCircle(900f, 1100f, 600f, paintSkin); //draw head
        //sclera drawing
        canvas.drawOval(500f, 800f, 800f, 1100f, white);
        canvas.drawOval(1000f, 800f, 1300f, 1100f, white);
        //pupil drawing
        canvas.drawCircle(650f, 950f, 120f, black);
        canvas.drawCircle(1150f, 950f, 120f, black);
        //iris drawing
        canvas.drawCircle(650f, 950f, 75f, paintEye);
        canvas.drawCircle(1150f, 950f, 75f, paintEye);
        //draw mouth
        canvas.drawArc(600f, 1200f, 1200f, 1500f, 0f, 180f, false, black);
        for (int i = 0; i < 520; i +=40)
            canvas.drawRect(650 + i, 1350, 680 + i, 1400, white);


    }

    /*
     * method draws hair from randomly selected hairstyle
     */
    private void drawHair(Canvas canvas, String hair) {
        if (hair.equals("Mohawk"))
            canvas.drawRect(700f, 100f, 1100f, 1000f, rndPaintHair);
        else if (hair.equals("Flat Top"))
            canvas.drawRect(310.0f, 300.0f, 1490.0f, 1200.f, rndPaintHair);
        else if (hair.equals("Afro"))
            canvas.drawCircle(900f, 700f, 700f, rndPaintHair);
        else
            return;
    }

    /*
     * method randomizes colors for eye, skin, hair and picks a random hairstyle
     */
    private void randomize() {
        /**
         External Citation
         Date: 12 February 2018
         Problem: didn't know how to loop through list of colors from strings.xml
         Resource:
         https://stackoverflow.com/questions/27992120/fill-arraylist-with-colors-for-android
         Solution: I used the example code from this post.
        */
        //
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

        //puts arrayHairstyle from strings.xml into arrayList
        List<String> hairText = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arrayHairstyle)));
        hairStyleString = hairText.get(rnd.nextInt(3-1) + 1);

        //gets random colors from given arrays or random integers and sets them to paint and RGB variables
        paintSkin = new Paint();
        paintSkin.setColor(skinColorsList.get(rnd.nextInt(5))); //takes random color from list
        redSkin = Color.red(paintSkin.getColor());
        greenSkin = Color.green(paintSkin.getColor());
        blueSkin = Color.blue(paintSkin.getColor());
        paintEye = new Paint();
        paintEye.setColor(eyeColorsList.get(rnd.nextInt(5))); //takes random color from list
        redEye = Color.red(paintEye.getColor());
        greenEye = Color.green(paintEye.getColor());
        blueEye = Color.blue(paintEye.getColor());
        rndPaintHair = new Paint(); //sets colors for face features
        redHair = rnd.nextInt(256);
        greenHair = rnd.nextInt(256);
        blueHair = rnd.nextInt(256);
        rndPaintHair.setARGB(0xFF, redHair, greenHair, blueHair);

        //sets all the colors to "global" variables so they can be used elsewhere
        Colors.getInstance().rHair = redHair;
        Colors.getInstance().gHair = greenHair;
        Colors.getInstance().bHair = blueHair;
        Colors.getInstance().rEye = redEye;
        Colors.getInstance().rEye = greenEye;
        Colors.getInstance().bEye = blueEye;
        Colors.getInstance().rSkin = redSkin;
        Colors.getInstance().gSkin = greenSkin;
        Colors.getInstance().bSkin = blueSkin;
    }
}
