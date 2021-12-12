package com.example.a3altareeq;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Ride;

import java.util.ArrayList;
import java.util.List;

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

        TextView driverName = holder.itemView.findViewById(R.id.driverNameInUserRidesFragment);
        TextView dateTime = holder.itemView.findViewById(R.id.timeinUserRidesFragment);
        TextView price = holder.itemView.findViewById(R.id.priceInUserRidesFragment);

        driverName.setText(holder.ride.getDriverName());
        dateTime.setText(holder.ride.getDateTime());
        price.setText(holder.ride.getPrice());
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
