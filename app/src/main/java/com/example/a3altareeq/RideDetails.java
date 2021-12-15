package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RideDetails extends AppCompatActivity {

    Context context;
    User usere;
    Ride ridee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RideDetails.this);
        String userId=sharedPreferences.getString("userId","userId");
        String rideId=getIntent().getStringExtra("rideId");

        // --------------------------
        double latpick= getIntent().getDoubleExtra("latt",0);
        double lonpick= getIntent().getDoubleExtra("lonn",0);
        double latdrop= getIntent().getDoubleExtra("latto",0);
        double londrop= getIntent().getDoubleExtra("lonto",0);
        String note =getIntent().getStringExtra("notee");
        String phoneDriver=getIntent().getStringExtra("driverPhoneNumber");
        String firstNameDriver=getIntent().getStringExtra("driverFirstName");
        String lastNameDriver=getIntent().getStringExtra("driverLastName");
        String date=getIntent().getStringExtra("dateTime");

        TextView driverName = findViewById(R.id.profileName);
        TextView phoneNumber=findViewById(R.id.profilePhone);
        TextView pickLocation=findViewById(R.id.pickLocationView);
        TextView dropLocation=findViewById(R.id.dropLocationView);
        TextView notes=findViewById(R.id.noteView);
        TextView dateTime =findViewById(R.id.dateTime);

        driverName.setText(firstNameDriver+" "+lastNameDriver);
        phoneNumber.setText(phoneDriver);
        notes.setText(note);
        dateTime.setText(date);

        pickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", latpick,lonpick);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        dropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", latdrop,londrop);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        // -----------------------------

        Button sendreq=findViewById(R.id.sendreq_btn);

        sendreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("rideIdddddddd"+ rideId );
                System.out.println("uuuusssseeerrrrr"+ userId );




                new SweetAlertDialog(RideDetails.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to cancel this ride!")
                        .setConfirmText("Confirm")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {


                                Amplify.API.query(
                                        ModelQuery.get(User.class,userId), response->{
                                            /*---------------*/
                                            Amplify.API.query(
                                                    ModelQuery.get(Ride.class,rideId),
                                                    responseRide -> {
                                                        RideUser rideUser = RideUser.builder().ride(responseRide.getData()).user(response.getData()).build();
                                                        Amplify.API.mutate(ModelMutation.create(rideUser),
                                                                responseRiderUser-> Log.i("MyAmplifyApp", "Added user with id: " + responseRiderUser.getData().getId()),
                                                                error -> Log.e("MyAmplifyApp", "Create failed", error)

                                                        );
                                                    },
                                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
                                            );
                                            /*----------------------------------------------------------*/
                                        },error -> Log.e("MyAmplifyApp", "Create failed", error));


                                sDialog
                                        .setTitleText("Success!")
                                        .setContentText("Your ride  has been confirmed!")
                                        .setConfirmText("OK").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent goToUserRidesPage=new Intent(getApplicationContext(),UserRides.class);
                                        startActivity(goToUserRidesPage);

                                    }
                                })
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                            }
                        })
                        .show();
            }
        });
    }

}