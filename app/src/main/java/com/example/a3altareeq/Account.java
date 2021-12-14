package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.User;

public class Account extends AppCompatActivity {
   private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Account.this);
        String userId=sharedPreferences.getString("userId","userId");

        TextView newUserName=findViewById(R.id.newUserName);
        EditText newFirstName=findViewById(R.id.newFirstName);
        EditText newLastName=findViewById(R.id.newLastName);
        EditText NewPhoneNumber=findViewById(R.id.newPhoneNumber);
        EditText newGender=findViewById(R.id.newGender);
        EditText newDateOfBrith=findViewById(R.id.newDateOfBrith);
        EditText newImage=findViewById(R.id.newImage);


        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.contains(Amplify.Auth.getCurrentUser().getUsername())),
                response -> {
                    for (User u : response.getData()) {
                        user=u;

                    }
                    System.out.println("************************************"+user.getUserName());
                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {
                            newUserName.setText(user.getUserName());
                            newFirstName.setText(user.getFirstName());
                            newLastName.setText(user.getLastName());
                            NewPhoneNumber.setText(user.getPhoneNumber());
                            newGender.setText(user.getGender());
                            newDateOfBrith.setText(user.getDateOfBirth());
                            newImage.setText(user.getPictureKey());
                        }
                    });
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Button updateBtn=findViewById(R.id.updateAccountButton);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("userId: ********************" + userId);
                Amplify.DataStore.query(User.class, Where.id(userId),
                        matches -> {

                            if (matches.hasNext()) {
                                User original = matches.next();
                                User edited = original.copyOfBuilder()
                                        .firstName(newFirstName.getText().toString())
                                        .lastName(newLastName.getText().toString())
                                        .phoneNumber(NewPhoneNumber.getText().toString())
                                        .gender(newGender.getText().toString())
                                        .dateOfBirth(newDateOfBrith.getText().toString())
                                        .pictureKey(newImage.getText().toString())
                                        .build();
                                Amplify.DataStore.save(edited,
                                        updated -> Log.i("MyAmplifyApp", "Updated a User."),
                                        failure -> Log.e("MyAmplifyApp", "Update failed.", failure)
                                );
                            }
                        },
                        failure -> Log.e("MyAmplifyApp", "Query failed.", failure)
                );
            }
        });
    }
}