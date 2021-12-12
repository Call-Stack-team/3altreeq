package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRides extends AppCompatActivity {
     List<Ride> allUserRides = new ArrayList<>();
     User user;
     RideUser ride;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rides);
    }

    @Override
   protected void onResume() {
        super.onResume();

        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.contains(Amplify.Auth.getCurrentUser().getUsername())),
                response -> {
                    for (User u : response.getData()) {
                        user=u;
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        RecyclerView recyclerView = findViewById(R.id.allUserRides);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        Amplify.API.query(
                ModelQuery.list(Ride.class),
                response -> {
                    for (Ride ride : response.getData()) {
                        allUserRides.add(ride);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("userRides", error.toString(), error)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(UserRides.this));
        recyclerView.setAdapter(new UserRidesAdapter(allUserRides));

    }
}