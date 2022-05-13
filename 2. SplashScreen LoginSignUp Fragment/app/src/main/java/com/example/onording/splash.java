package com.example.onording;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    private static int splash_Scree = 5000;
    Animation topAnim ,bottomAnim;
    ImageView image;
    ImageView background ;
    TextView logo ;
    // private LottieAnimationView lottieAnimationView;  //global define




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //animation
        topAnim = AnimationUtils.loadAnimation(this , R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this , R.anim.bottom_animation);
        background = findViewById(R.id.background);
        image = findViewById(R.id.LogoPng);
        logo = findViewById(R.id.bienvenuTxt);
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        logo.animate().translationX(1600).setDuration(1000).setStartDelay(4000);
        image.animate().translationX(1600).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, splash_Scree);





    }
}