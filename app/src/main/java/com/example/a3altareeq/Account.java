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
        TextView newDateOfBrith=findViewById(R.id.newDateOfBrith);
//        EditText newImage=findViewById(R.id.newImage);


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
//                            newImage.setText(user.getPictureKey());
                        }
                    });
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Button updateBtn=findViewById(R.id.updateAccountButton);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
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
//                                        .pictureKey(newImage.getText().toString())
                                        .build();
                                Amplify.DataStore.save(edited,
                                        updated -> Log.i("MyAmplifyApp", "Updated a User."),
                                        failure -> Log.e("MyAmplifyApp", "Update failed.", failure)
                                );
                            }
                        },
                        failure -> Log.e("MyAmplifyApp", "Query failed.", failure)
                );
                startActivity(new Intent(Account.this,AccountShow.class));
            }
        });

        ////////////////////////Input by User///////////////////

        //Date Picker
        TextView dateOfBrith=findViewById(R.id.newDateOfBrith);
        dateOfBrith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog  = new DatePickerDialog(Account.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateOfBrith.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
}