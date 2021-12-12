package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ConstraintLayout layout = findViewById(R.id.layoutlogin);
//        AnimationDrawable animationDrawable = (AnimationDrawable)
//                layout.getBackground();
//        animationDrawable.setEnterFadeDuration(1500);
//        animationDrawable.setExitFadeDuration(3000);
//        animationDrawable.start();


        TextView goToSignUpPage=findViewById(R.id.goToSignUp);
        Button logInButton=findViewById(R.id.loginButton);
        EditText userName=findViewById(R.id.usernamelogin);
        EditText password=findViewById(R.id.passwordlogin);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signIn(
                        userName.getText().toString(),
                        password.getText().toString(),
                        result ->{ Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                            Intent goToMain = new Intent(Login.this, MainActivity.class);
                            startActivity(goToMain);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

        goToSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegister = new Intent(Login.this, Register.class);
                startActivity(goToRegister);
            }
        });

    }
}