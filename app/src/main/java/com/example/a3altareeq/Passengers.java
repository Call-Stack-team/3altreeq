package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class Passengers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);
    }

    @Override
    protected  void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Passengers.this);
        String userId=sharedPreferences.getString("userId","id");

        String offerRideId=getIntent().getStringExtra("offerRideId");

        RecyclerView recyclerView = findViewById(R.id.passengerRecy);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<User> allPassenger = new ArrayList<>();

        Amplify.API.query(ModelQuery.get(Ride.class,offerRideId),
                rideResponse->{
                    for (RideUser rideUser:rideResponse.getData().getRideUsers()) {

                        if(!rideUser.getUser().getUserName().equals(rideResponse.getData().getDriverName())){
                        allPassenger.add(rideUser.getUser());
                        }
                    }
                    handler.sendEmptyMessage(1);

                },e->Log.e("e",e.getMessage())
                );

        recyclerView.setLayoutManager(new LinearLayoutManager(Passengers.this));
        recyclerView.setAdapter(new PassengerAdapter(allPassenger));
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }
}