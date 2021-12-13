package com.example.a3altareeq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RideDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RideDetails.this);
        String userId=sharedPreferences.getString("userId","userId");
        String rideId=getIntent().getStringExtra("rideId");

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