package com.example.kipasangin;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {

    ToggleButton siap;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    int index;

    final int SPEED [] = {0,5000,3000,1000,20000};

    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siap = (ToggleButton) findViewById(R.id.pasti);
        seekBar = (SeekBar)findViewById(R.id.seekBar3);
        imageView = (ImageView)findViewById(R.id.imageView);

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation",0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());



        siap.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(siap.isChecked()==true){
                 rotateAnimator.setDuration(SPEED[index]);
                 rotateAnimator.start();
             }
             else{
                 rotateAnimator.end();
             }
             }
        });


//        //turn on the lights
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        switchButton = (Switch)findViewById(R.id.switchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchButton.isChecked()==true){
                    gd.setColors(new int[]{YELLOW, TRANSPARENT});
                    imageView.setBackground(gd);
                }
                else{
                    imageView.setBackgroundColor(TRANSPARENT);

                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser==true){
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                }
                else{
                    rotateAnimator.end();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
