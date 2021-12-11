//package com.example.a3altareeq;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.amplifyframework.core.Amplify;
//
//public class ConfirmEmail extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_confirm_email);
//        Button confirmCode=findViewById(R.id.confirmButton);
//        EditText code=findViewById(R.id.code);
//        String username = getIntent().getExtras().getString("Name");
//        confirmCode.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Amplify.Auth.confirmSignUp(
//                        username,
//                        code.getText().toString(),
//                        result -> {
//                            Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
//                            Intent goToSignIn = new Intent(ConfirmEmail.this, Login.class);
//                            startActivity(goToSignIn);
//                        },
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//            }
//        });
//
//
//    }
//}