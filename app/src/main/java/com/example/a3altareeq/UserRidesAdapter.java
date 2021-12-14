package com.example.a3altareeq;


import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        Set<String> passengers=new HashSet<>();

        Amplify.API.query(ModelQuery.get(Ride.class,holder.ride.getId()),
                response->{
            for (RideUser ru:response.getData().getRideUsers()){
                 passengers.add(ru.getUser().getFirstName()+" "+ru.getUser().getLastName()+" "+ru.getUser().getPhoneNumber());
                System.out.println(ru.getUser().getFirstName()+" "+ru.getUser().getLastName()+" "+ru.getUser().getPhoneNumber());

            }

                },e-> Log.e("aaa",e.getMessage())
                );
        TextView driverName = holder.itemView.findViewById(R.id.driverNameInUserRidesFragment);
        TextView dateTime = holder.itemView.findViewById(R.id.timeinUserRidesFragment);
        TextView price = holder.itemView.findViewById(R.id.priceInUserRidesFragment);
        Button viewPassenger=holder.itemView.findViewById(R.id.passenger);
        driverName.setText(holder.ride.getDriverName());
        dateTime.setText(holder.ride.getDateTime());
        price.setText(holder.ride.getPrice());

        viewPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(holder.itemView.getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Passengers")
                        .setContentText(
                                passengers.toString()
                        )
                        .show();
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
}
