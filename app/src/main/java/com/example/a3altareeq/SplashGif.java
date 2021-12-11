package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
//import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.bumptech.glide.Glide;


public class SplashGif extends AppCompatActivity {

    private static int DELAY_TIME = 6000;

    Animation topAnim, bottomAnim;
    TextView app_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_gif);

//
//        try {
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        ImageView imageView = findViewById(R.id.img);
        Glide.with(this).load(R.drawable.cargif).into(imageView);
        app_name = findViewById(R.id.appname);

        imageView.setAnimation(bottomAnim);
        app_name.setAnimation(topAnim);
//        Intent goToFindeRidePage=new Intent(this,FindRide.class);
//        startActivity(goToFindeRidePage);
        Intent goTOfferRidePage=new Intent(this,OfferRide.class);
        startActivity(goTOfferRidePage);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent i = new Intent(SplashGif.this,Login.class);
//                startActivity(i);
//                finish();
//
//            }
//        },DELAY_TIME);


    }
}