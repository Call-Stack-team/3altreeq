package com.example.a3altareeq;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class OfferRide extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LatLng mOrigin;
    private LatLng mDestination;
    private Polyline mPolyline;

    ArrayList<LatLng> mMarkerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_offer_ride);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapOffer);

        mapFragment.getMapAsync(this);

        mMarkerPoints = new ArrayList<>();

        ////////////////////////Input by User///////////////////

        //Date Picker
        TextView datePickerFeild=findViewById(R.id.editRideDateOffer);
        datePickerFeild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog  = new DatePickerDialog(OfferRide.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                datePickerFeild.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //Time Picker
        TextView timePickerFeild=findViewById(R.id.editRideTimeOffer);
        timePickerFeild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(OfferRide.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

                        mcurrentTime.set(Calendar.HOUR_OF_DAY,selectedHour);
                        mcurrentTime.set(Calendar.MINUTE,selectedMinute);
                        String timeFormat = simpleDateFormat.format(mcurrentTime.getTime());
                        timePickerFeild.setText(timeFormat);

                    }
                }, hour, minute, false);//Is 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        //Details for the offer
        TextView seats=findViewById(R.id.editSeatsNumber);
        TextView price=findViewById(R.id.editPriceOffered);
        TextView offerNotes=findViewById(R.id.editRideNotes);




        //
        Button offerRide=findViewById(R.id.offerRideButton);
        offerRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(OfferRide.this);
                String id=sharedPreferences.getString("userId","id");

                Ride ride = Ride.builder().driverName(Amplify.Auth.getCurrentUser().getUsername())
                        .dateTime(datePickerFeild.getText().toString() + " " + timePickerFeild.getText().toString())
                        .numberOfSeats(Integer.valueOf(seats.getText().toString())).price(price.getText().toString())
                        .latDrop(mDestination.latitude).latPick(mOrigin.latitude)
                        .lonDrop(mDestination.longitude).lonPick(mOrigin.longitude).note(offerNotes.getText().toString()).build();



                List<User> list = new ArrayList<>();
                Amplify.API.query(
                        ModelQuery.get(User.class,id), response->{

                            Log.i("MyAmplifyApp", "Added user with id: " + response.getData().getId());
                                /*---------------*/

                            Amplify.API.mutate(
                                    ModelMutation.create(ride),
                                    responseRide -> {
                                        RideUser rideUser = RideUser.builder().ride(responseRide.getData()).user(response.getData()).build();
                                        Amplify.API.mutate(ModelMutation.create(rideUser),
                                                responseRiderUser-> Log.i("MyAmplifyApp", "Added user with id: " + responseRiderUser.getData().getId()),
                                                error -> Log.e("MyAmplifyApp", "Create failed", error)

                                                );


                                        Log.i("MyAmplifyApp", "Added user with id: " + response.getData().getId());
                                        Intent goOfferdList = new Intent(OfferRide.this, UserRideBooked.class);
                                        startActivity(goOfferdList);
                                    },
                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
                            );
                            /*----------------------------------------------------------*/
//                            response.getData().getRides().add(rideUser);
                        },error -> Log.e("MyAmplifyApp", "Create failed", error));




                Log.i("AuthQuickStart", "Result:  ");
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        zoomCountryLevel("Jordan");
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng point) {
                // Already two locations
                if(mMarkerPoints.size()>1){
                    mMarkerPoints.clear();
                    mMap.clear();
                }

                // Adding new item to the ArrayList
                mMarkerPoints.add(point);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                /*
                 * For the start location, the color of marker is GREEN and
                 * for the end location, the color of marker is RED.
                 */

                if(mMarkerPoints.size()==1){
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                }else if(mMarkerPoints.size()==2){
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }

                // Add new marker to the Google Map Android API V2
                mMap.addMarker(options);

                // Checks, whether start and end locations are captured
                if(mMarkerPoints.size() >= 2){

                    mOrigin = mMarkerPoints.get(0);
                    System.out.println("*****************"+mOrigin );
                    mDestination = mMarkerPoints.get(1);
                    System.out.println("*****************"+mDestination );

                    //set the pick point
                    String pickPointAddress=getAddress(OfferRide.this,mOrigin.latitude,mOrigin.longitude);
                    TextView pickPoint=findViewById(R.id.enterPickUpPointOffer);
                    pickPoint.setText(pickPointAddress);

                    //set the drop point
                    String dropPointAddress=getAddress(OfferRide.this,mDestination.latitude,mDestination.longitude);
                    TextView dropPoint=findViewById(R.id.enterDropPointOffer);
                    dropPoint.setText(dropPointAddress);

                    //draw the path between to points
                    drawRoute();
                }

            }
        });
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
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 7));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get the location information
    public String getAddress(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            System.out.println("**************"+addresses.toString());

            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();
            add=add+"\n"+obj.getFeatureName();

            System.out.println("++++++++++++++++++++"+add.toString());
            return add;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void drawRoute(){

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mOrigin, mDestination);

        OfferRide.DownloadTask downloadTask = new OfferRide.DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }


    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Key
        String key = "key=" + getString(R.string.google_maps_key);

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception on download", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /** A class to download data from Google Directions URL */
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                JSONObject jsonObject = new JSONObject(data); // parse response into json object
                JSONObject routeObject = jsonObject.getJSONObject("route"); // pull out the "route" object
                JSONObject durationObject = jsonObject.getJSONObject("duration"); // pull out the "duration" object
                String duration = durationObject.getString("text");
                Log.d("DownloadTask","DownloadTask : " + data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            OfferRide.ParserTask parserTask = new OfferRide.ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Directions in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    Log.i("lat", String.valueOf(((int) lat)));
                    Log.i("lng", String.valueOf(((int) lng)));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(8);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                if(mPolyline != null){
                    mPolyline.remove();
                }
                mPolyline = mMap.addPolyline(lineOptions);

            }else
                Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
        }
    }

}