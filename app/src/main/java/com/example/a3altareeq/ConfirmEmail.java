package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

public class ConfirmEmail extends AppCompatActivity {
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);

        Button confirmCode=findViewById(R.id.confirmButton);
        EditText code=findViewById(R.id.code);
        String username = getIntent().getExtras().getString("Name");
        String firstName = getIntent().getExtras().getString("firstName");
        String lastName = getIntent().getExtras().getString("lastName");
        String phoneNumber = getIntent().getExtras().getString("phoneNumber");
        String email = getIntent().getExtras().getString("Email");
        confirmCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Amplify.Auth.confirmSignUp(
                        username,
                        code.getText().toString(),
                        result -> {
                            if(result.isSignUpComplete()){
                                user= User.builder().userName(username).firstName(firstName).lastName(lastName).email(email).phoneNumber(phoneNumber).build();
                                Amplify.API.mutate(
                                        ModelMutation.create(user),
                                        response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                                );
                                System.out.println("siiiiiiigggn");
                            }

                            Intent goToSignIn = new Intent(ConfirmEmail.this, Login.class);
                            startActivity(goToSignIn);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });


    }
}