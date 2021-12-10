package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.registerButton);
        EditText username=findViewById(R.id.username);
        EditText firstName=findViewById(R.id.fname);
        EditText lastName=findViewById(R.id.lname);
        EditText email=findViewById(R.id.email);
        EditText phoneNumber=findViewById(R.id.phonenumber);
        EditText password=findViewById(R.id.password);


    registerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
                    .build();
            Amplify.Auth.signUp(username.getText().toString(), password.getText().toString(), options,
                    result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                    error -> Log.e("AuthQuickStart", "Sign up failed", error)
            );

        }
    });



    }
}