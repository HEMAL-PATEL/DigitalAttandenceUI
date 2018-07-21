package com.msttech.digitalattendence.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.ui.home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    Animation animation;

    ImageView justLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        justLogo = (ImageView) findViewById(R.id.just_logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotation_anim);
        justLogo.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
