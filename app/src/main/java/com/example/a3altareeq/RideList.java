package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
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
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class RideList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_list);

//       Ride ride= Ride.builder().driverName("mm").dateTime("date").numberOfSeats(4).price("66").latDrop(1.4).latPick(2.2).lonDrop(4.2).lonPick(44.4).build();
//        Amplify.API.mutate(
//                ModelMutation.create(ride),
//                response -> Log.i("MyAmplifyApp", "Added Ride with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//        System.out.println("new Ride");
  }


    @Override
    protected  void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.allRideRecycleView);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Ride> allRide = new ArrayList<>();
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String teamId = sharedPreferences.getString("teamID","null");

        Amplify.API.query(
                ModelQuery.list(Ride.class),
                response -> {
                    for (Ride ride: response.getData()) {
                        allRide.add(ride);
                        Log.i("iddd",ride.getId());
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(RideList.this));
        recyclerView.setAdapter(new RideAdapter(allRide));
    }
}