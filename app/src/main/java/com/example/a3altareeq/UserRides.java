package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserRides extends AppCompatActivity {
     List<Ride> allUserRides = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rides);
        Button booked=findViewById(R.id.offered_btn);
        booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(UserRides.this,UserRideBooked.class));
            }
        });

    }

    @Override
   protected void onResume() {
        super.onResume();
        /* ------------------------------get auth user id from  main---------------*/

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UserRides.this);
        String id=sharedPreferences.getString("userId","id");

/*---------------------get and provide user rides---------------------------------*/

        RecyclerView recyclerView = findViewById(R.id.allUserRides);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        Amplify.API.query(ModelQuery.get(User.class,id),
                response ->{
                    for (RideUser rideUser :response.getData().getRides()) {
                        if(!rideUser.getRide().getDriverName().equals(Amplify.Auth.getCurrentUser().getUsername())) {
                            allUserRides.add(rideUser.getRide());
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error->Log.e("tareq",error.toString(),error)
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(UserRides.this));
        recyclerView.setAdapter(new UserRidesAdapter(allUserRides));

        /*---------------------------------------------------------------------------------*/

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }

}