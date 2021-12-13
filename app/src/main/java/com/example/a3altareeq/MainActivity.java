package com.example.a3altareeq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                } else if (itemId == R.id.item5) {
                    System.out.println("ride list");
                    startActivity(new Intent(MainActivity.this,RideList.class));
                }else if (itemId == R.id.item6) {
                    System.out.println("find Ride");
                    startActivity(new Intent(MainActivity.this,FindRide.class));
                } else if (itemId == R.id.item7) {
                    System.out.println("offer ride");
                    startActivity(new Intent(MainActivity.this,OfferRide.class));
                }else if (itemId == R.id.item8) {
                    System.out.println("alert");
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("Won't be able to recover this file!")
                            .setConfirmText("Yes,delete it!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Deleted!")
                                            .setContentText("Your imaginary file has been deleted!")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                }
                            })
                            .show();

                }

                drawerLayout.close();
                return true;


            }


        });

//        Button login = findViewById(R.id.logBut);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,Register.class));
//            }
//        });
//Button in=findViewById(R.id.button2);
//in.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        startActivity(new Intent(MainActivity.this,Login.class));
//    }
//});
//
//Button signOut=findViewById(R.id.signout);
//
//signOut.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//        Amplify.Auth.signOut(
//                () -> {Log.i("AuthQuickstart", "Signed out successfully");
//                    Intent goToSignIn = new Intent(MainActivity.this, Login.class);
//                    startActivity(goToSignIn);
//                },
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//    }
//});
//
//Button userRidesButton=findViewById(R.id.userRideButtonMain);
//userRidesButton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        startActivity(new Intent(MainActivity.this,UserRides.class));
//    }
//});

//Button rideLsit=findViewById(R.id.rideList);
//        rideLsit.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        startActivity(new Intent(MainActivity.this,RideList.class));
//    }
//});
//
//Button findRide=findViewById(R.id.findRide);
//        findRide.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        startActivity(new Intent(MainActivity.this,FindRide.class));
//    }
//});
//
//        Button offerRide=findViewById(R.id.offerRide);
//        offerRide.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        startActivity(new Intent(MainActivity.this,OfferRide.class));
//    }
//});
//        Button alert=findViewById(R.id.alert);
//        alert.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
////        new SweetAlertDialog(MainActivity.this)
////                .setTitleText("Here's a message!")
////                .show();
//        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setConfirmText("Yes,delete it!")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog
//                                .setTitleText("Deleted!")
//                                .setContentText("Your imaginary file has been deleted!")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                    }
//                })
//                .show();
//
//    }
//});

/*------------------------send auth user id to user ride page--------------------------------- */
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.contains(Amplify.Auth.getCurrentUser().getUsername())),
                response -> {
                    for (User u : response.getData()) {
                        user=u;
                    }
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    sharedPreferences.edit().putString("userId",user.getId()).apply();
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        /*-----------------------------------------------------------------------------------------*/

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

}