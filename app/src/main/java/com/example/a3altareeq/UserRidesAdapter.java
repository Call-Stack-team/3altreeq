package com.example.a3altareeq;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Ride;
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserRidesAdapter extends RecyclerView.Adapter<UserRidesAdapter.UserRidesViewHolder>{
    private List<Ride> allUserRides=new ArrayList<Ride>();

    public UserRidesAdapter(List<Ride> allUserRides) {
        this.allUserRides = allUserRides;
    }
    public static class UserRidesViewHolder extends RecyclerView.ViewHolder{
        public Ride ride;
        public View itemView;

        public UserRidesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }
    @NonNull
    @Override
    public UserRidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user_rides,parent,false);
        UserRidesViewHolder userRidesViewHolder =new UserRidesViewHolder(view);
        return userRidesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRidesViewHolder holder, int position) {
        holder.ride=allUserRides.get(position);
//        Set<String> passengers=new HashSet<>();
        Button viewPassenger=holder.itemView.findViewById(R.id.passenger);

        Amplify.API.query(ModelQuery.get(Ride.class,holder.ride.getId()),
                response->{
            for (RideUser ru:response.getData().getRideUsers()){
                if (!ru.getRide().getDriverName().equals(Amplify.Auth.getCurrentUser().getUsername())){
                    viewPassenger.setVisibility(View.INVISIBLE);
                }
//                 passengers.add(ru.getUser().getFirstName()+" "+ru.getUser().getLastName()+" "+ru.getUser().getPhoneNumber());

            }
            },e-> Log.e("aaa",e.getMessage())
                );
        TextView driverName = holder.itemView.findViewById(R.id.driverNameInUserRidesFragment);
        TextView dateTime = holder.itemView.findViewById(R.id.timeinUserRidesFragment);
        TextView price = holder.itemView.findViewById(R.id.priceInUserRidesFragment);
        TextView pickPoint=holder.itemView.findViewById(R.id.pickInUserRidesFragment);
        TextView dropPoint=holder.itemView.findViewById(R.id.dropInUserRidesFragment);

        driverName.setText(holder.ride.getDriverName());
        dateTime.setText(holder.ride.getDateTime());
        price.setText(holder.ride.getPrice());
        String pickLocation=getAddress(holder.itemView.getContext(),holder.ride.getLatPick(),holder.ride.getLonPick());
        String dropLocation=getAddress(holder.itemView.getContext(),holder.ride.getLatDrop(),holder.ride.getLonDrop());
        pickPoint.setText(pickLocation);
        dropPoint.setText(dropLocation);


        viewPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPassengerActivity=new Intent(holder.itemView.getContext(),Passengers.class);
                goToPassengerActivity.putExtra("offerRideId",holder.ride.getId());
                view.getContext().startActivity(goToPassengerActivity);
            }

        });


    }

    @Override
    public int getItemCount() {
        return allUserRides.size();
    }

    public List<Ride> getAllUserRides() {
        return allUserRides;
    }

    public void setAllUserRides(List<Ride> allUserRides) {
        this.allUserRides = allUserRides;
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
