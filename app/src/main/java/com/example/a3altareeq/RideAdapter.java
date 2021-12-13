package com.example.a3altareeq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.RideViewHolder> {
    List<Ride> allRide;
    User u;

    public RideAdapter(List<Ride> allRide) {
        this.allRide = allRide;
    }

    public static class RideViewHolder extends RecyclerView.ViewHolder{

        public Ride ride;
        public View itemView;

        public RideViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ride,parent,false);
        RideViewHolder rideViewHolder=new RideViewHolder(view);
        return rideViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        holder.ride=allRide.get(position);
        Intent goToRideDetails =new Intent(holder.itemView.getContext(),RideDetails.class);
        /*--------------------------------------------*/
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.contains(holder.ride.getDriverName())),
                response -> {
                    for (User us : response.getData()) {
                        u=us;
                    }
                    goToRideDetails.putExtra("driverPhoneNumber",u.getPhoneNumber());
                    goToRideDetails.putExtra("driverFirstName",u.getFirstName());
                    goToRideDetails.putExtra("driverLastName",u.getLastName());
                    },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        /*-------------------------------------------*/


        TextView riderName=holder.itemView.findViewById(R.id.testFragmint);
        TextView from=holder.itemView.findViewById(R.id.from);
        TextView to=holder.itemView.findViewById(R.id.to);
        TextView price=holder.itemView.findViewById(R.id.price);
        TextView numberOfSeat=holder.itemView.findViewById(R.id.numberOfSeat);
        riderName.setText(holder.ride.getDriverName());
        from.setText(getAddress(holder.itemView.getContext(),holder.ride.getLatPick(),holder.ride.getLonPick()));
        to.setText(getAddress(holder.itemView.getContext(),holder.ride.getLatDrop(),holder.ride.getLonDrop()));
        price.setText(holder.ride.getPrice());
        numberOfSeat.setText(holder.ride.getRideUsers().size()-1+"/"+holder.ride.getNumberOfSeats());

        Button button=holder.itemView.findViewById(R.id.request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToRideDetails.putExtra("rideId",holder.ride.getId());
                //
                goToRideDetails.putExtra("latt",holder.ride.getLatPick());
                goToRideDetails.putExtra("lonn",holder.ride.getLonPick());
                goToRideDetails.putExtra("notee",holder.ride.getNote());
                goToRideDetails.putExtra("namee",holder.ride.getDriverName());
                goToRideDetails.putExtra("lonto",holder.ride.getLonDrop());
                goToRideDetails.putExtra("latto",holder.ride.getLatDrop());
//                Log.i("dfghjkjhgfghjkhg",holder.ride.getRideUsers().get(1).getUser()+"");
                view.getContext().startActivity(goToRideDetails);
            }
        });


    }

    @Override
    public int getItemCount() {
        return allRide.size();
    }


    public String getAddress(Context context, double pickPointLat, double pickPointLon) {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
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
}
