package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.User;

import java.util.Calendar;

public class AccountShow extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_show);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AccountShow.this);
        String userId=sharedPreferences.getString("userId","userId");

        TextView newUserName=findViewById(R.id.newUserName);
        TextView newFirstName=findViewById(R.id.newFirstName);
        TextView newLastName=findViewById(R.id.newLastName);
        TextView NewPhoneNumber=findViewById(R.id.newPhoneNumber);
        TextView newGender=findViewById(R.id.newGender);
        TextView newDateOfBrith=findViewById(R.id.newDateOfBrith);


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
                            newUserName.setText("@"+user.getUserName());
                            newFirstName.setText(user.getFirstName());
                            newLastName.setText(user.getLastName());
                            NewPhoneNumber.setText(user.getPhoneNumber());
                            newGender.setText(user.getGender());
                            newDateOfBrith.setText(user.getDateOfBirth());
                        }
                    });
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Button goToAccount=findViewById(R.id.goToAccount);
        goToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountShow.this,Account.class));
            }
        });

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }
}