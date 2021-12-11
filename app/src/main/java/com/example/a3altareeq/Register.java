//package com.example.a3altareeq;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import android.content.Intent;
//import android.graphics.drawable.AnimationDrawable;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.amplifyframework.auth.AuthUserAttributeKey;
//import com.amplifyframework.auth.options.AuthSignUpOptions;
//import com.amplifyframework.core.Amplify;
//
//public class Register extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        ConstraintLayout layout = findViewById(R.id.layout);
//        AnimationDrawable animationDrawable = (AnimationDrawable)
//                layout.getBackground();
//        animationDrawable.setEnterFadeDuration(1500);
//        animationDrawable.setExitFadeDuration(3000);
//        animationDrawable.start();
//
//
//        TextView goToSignIn=findViewById(R.id.gotosignin);
//
//        Button registerButton = findViewById(R.id.registerButton);
//        EditText username=findViewById(R.id.username);
//        EditText firstName=findViewById(R.id.fname);
//        EditText lastName=findViewById(R.id.lname);
//        EditText email=findViewById(R.id.email);
//        EditText phoneNumber=findViewById(R.id.phonenumber);
//        EditText password=findViewById(R.id.password);
//
//
//    registerButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            AuthSignUpOptions options = AuthSignUpOptions.builder()
//                    .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
//                    .build();
//            Amplify.Auth.signUp(username.getText().toString(), password.getText().toString(), options,
//                    result -> {Log.i("AuthQuickStart", "Result: " + result.toString());
//                        Intent goToConfirm = new Intent(Register.this, ConfirmEmail.class);
//                        goToConfirm.putExtra("Name", username.getText().toString());
//                        goToConfirm.putExtra("Email", email.getText().toString());
//                        startActivity(goToConfirm);
//
//
//                    },
//                    error -> Log.e("AuthQuickStart", "Sign up failed", error)
//            );
//
//        }
//    });
//
//        goToSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToSignInActivity = new Intent(Register.this, Login.class);
//                startActivity(goToSignInActivity);
//            }
//        });
//
//    }
//}