package com.example.a3altareeq;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    User user;
    ArrayList<LatLng> mMarkerPoints;

    //
    private GoogleMap mMap;
    private LatLng mOrigin;
    private LatLng mDestination;
    private Polyline mPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////////////////
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        mMarkerPoints = new ArrayList<>();

        Button find=findViewById(R.id.findride);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goOfferd= new Intent(MainActivity.this, FindRide.class);
                startActivity(goOfferd);
            }
        });

        Button start=findViewById(R.id.startride);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goOfstart= new Intent(MainActivity.this, OfferRide.class);
                startActivity(goOfstart);
            }
        });

        //////////////////////////////////

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

        //****************** side bar functions *************************//

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });


        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();
                if (itemId == R.id.item1) {
                    System.out.println("sign up");
                    startActivity(new Intent(MainActivity.this,Register.class));
                } else if (itemId == R.id.item2) {
                    System.out.println("sign in");
                    startActivity(new Intent(MainActivity.this,Login.class));
                } else if (itemId == R.id.item3) {
                    System.out.println("sign out");
                    Amplify.Auth.signOut(
                            () -> {Log.i("AuthQuickstart", "Signed out successfully");
                                Intent goToSignIn = new Intent(MainActivity.this, Login.class);
                                startActivity(goToSignIn);
                            },
                            error -> Log.e("AuthQuickstart", error.toString())
                    );
                }else if (itemId == R.id.item4) {
                    System.out.println("user ride");
                    startActivity(new Intent(MainActivity.this,UserRides.class));
                }else if (itemId == R.id.item6) {
                    System.out.println("find Ride");
                    startActivity(new Intent(MainActivity.this,FindRide.class));
                } else if (itemId == R.id.item7) {
                    System.out.println("offer ride");
                    startActivity(new Intent(MainActivity.this,OfferRide.class));
                }else if (itemId == R.id.item8) {
                    System.out.println("account");
                    startActivity(new Intent(MainActivity.this,AccountShow.class));
//                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("Are you sure?")
//                            .setContentText("Won't be able to recover this file!")
//                            .setConfirmText("Yes,delete it!")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog
//                                            .setTitleText("Deleted!")
//                                            .setContentText("Your imaginary file has been deleted!")
//                                            .setConfirmText("OK")
//                                            .setConfirmClickListener(null)
//                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                                }
//                            })
//                            .show();

                }

                drawerLayout.close();
                return true;


            }


        });


/*------------------------send auth user id to user ride page--------------------------------- */
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.contains(Amplify.Auth.getCurrentUser().getUsername())),
                response -> {
                    for (User u : response.getData()) {
                        user=u;

                    }
                    System.out.println("************************************"+user.getUserName());

//
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    sharedPreferences.edit().putString("userId",user.getId()).apply();
                    sharedPreferences.edit().putString("userSide",user.getUserName()).apply();
                    sharedPreferences.edit().putString("PhoneSide",user.getPhoneNumber()).apply();

                    /*-----------send user name and phone number to sidebar page------------- */
                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            String userName = sharedPreferences.getString("userSide","No User");
                            System.out.println(userName);
                            String phoneNumber = sharedPreferences.getString("PhoneSide","No User");
                            TextView sideDriverName = findViewById(R.id.sidebarName);
                            TextView sideDriverPhone = findViewById(R.id.sidebarPhone);
                            sideDriverName.setText(userName);
                            sideDriverPhone.setText(phoneNumber);
                        }
                    });
                    /*----------------------------------------------------------- */

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        /*-----------------------------------------------------------------------------------------*/


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        zoomCountryLevel("Jordan");

    }
    //make the map zoomed on Jordan at starting of activity
    public void zoomCountryLevel(String countryName){
        try {
            List<Address> address = new Geocoder(this).getFromLocationName(countryName, 1);
            if (address == null) {
                Log.e(TAG, "Not found");
            } else {
                Address loc = address.get(0);
                Log.e(TAG, loc.getLatitude() + " " + loc.getLongitude());
                LatLng pos = new LatLng(loc.getLatitude(), loc.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 6));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

