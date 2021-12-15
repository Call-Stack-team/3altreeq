package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RideList extends AppCompatActivity {
    String passengerCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_list);


  }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected  void onResume() {
        super.onResume();

        /*------------------passenger location---------------*/
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RideList.this);
        float pickPointLat=getIntent().getFloatExtra("pickPointLat",0);
        float pickPointLon=getIntent().getFloatExtra("pickPointLon",0);
        Location locationPassenger=new Location("");
        passengerCity= getAddress(RideList.this,pickPointLat,pickPointLon);
        locationPassenger.setLatitude(pickPointLat);
        locationPassenger.setLongitude(pickPointLon);
        /*----------------------------------------------*/
        String id=sharedPreferences.getString("userId","id");

        RecyclerView recyclerView = findViewById(R.id.allRideRecycleView);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Ride> allRide = new ArrayList<>();


        Amplify.API.query(
                ModelQuery.list(Ride.class),
                response -> {
                    for (Ride ride: response.getData()) {
                        List<String> usersName=new ArrayList<>();
                        for (RideUser ru:ride.getRideUsers()) {
                            usersName.add(ru.getUser().getUserName());
                            System.out.println(ru.getUser().getUserName());
                        }

                          Location locationDriver=new Location("");
                          locationDriver.setLatitude(ride.getLatPick());
                          locationDriver.setLongitude(ride.getLonPick());

                          if(locationPassenger.distanceTo(locationDriver)<10000){
                              if (!Amplify.Auth.getCurrentUser().getUsername().equals(ride.getDriverName())){
                                  if(!usersName.contains(Amplify.Auth.getCurrentUser().getUsername()))
                                  {
                                      if(usersName.size()-1<ride.getNumberOfSeats()){

                                          /*-------*/
//                                          try {
//                                              if(isLaterThanNow(ride.getDateTime()))
//                                                  allRide.add(ride);
//                                          } catch (ParseException e) {
//                                              e.printStackTrace();
//                                          }
                                          /*-------------*/

                                      allRide.add(ride);
                                                  }}}}
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(RideList.this));
        recyclerView.setAdapter(new RideAdapter(allRide));
    }



    public String getAddress(Context context, double pickPointLat, double pickPointLon) {
        Geocoder gcd = new Geocoder(RideList.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(pickPointLat, pickPointLon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        else {
            // do your stuff
            return "address not defined";
        }
    }


//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public boolean isLaterThanNow(String dateTime) throws ParseException {
//        // convert String to LocalDateTime
//        LocalDateTime offerTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/uuuu h:mm a", Locale.ENGLISH));
//
//        //print the dateTime in new format
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss a");
//        System.out.println(offerTime.format(formatter));
//
//        LocalDateTime timeNow= LocalDateTime.now();
//        int compareValue = offerTime.compareTo(timeNow);
//
//        System.out.println("Compare value = " + compareValue);
//
//        if(compareValue > 0)
//        {
//            System.out.println("dateTimeOne is later than dateTimeTwo");
//            return true;
//        }
//        return false;
//    }

}