package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        Button login = findViewById(R.id.logBut);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
Button in=findViewById(R.id.button2);
in.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,Login.class));
    }
});

Button signOut=findViewById(R.id.signout);

signOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Amplify.Auth.signOut(
                () -> {Log.i("AuthQuickstart", "Signed out successfully");
                    Intent goToSignIn = new Intent(MainActivity.this, Login.class);
                    startActivity(goToSignIn);
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
});

Button userRidesButton=findViewById(R.id.userRideButtonMain);
userRidesButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,UserRides.class));
    }
});

Button rideLsit=findViewById(R.id.rideList);
        rideLsit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,RideList.class));
    }
});

Button findRide=findViewById(R.id.findRide);
        findRide.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,FindRide.class));
    }
});

        Button offerRide=findViewById(R.id.offerRide);
        offerRide.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,OfferRide.class));
    }
});
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

}