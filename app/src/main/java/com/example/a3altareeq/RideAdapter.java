package com.example.a3altareeq;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Ride;

import java.util.List;

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.RideViewHolder> {
    List<Ride> allRide;

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
        TextView riderName=holder.itemView.findViewById(R.id.testFragmint);
        TextView from=holder.itemView.findViewById(R.id.from);
        TextView to=holder.itemView.findViewById(R.id.to);
        TextView phone=holder.itemView.findViewById(R.id.phone);
        TextView price=holder.itemView.findViewById(R.id.price);
        riderName.setText(holder.ride.getDriverName());
        from.setText(holder.ride.getLatDrop().toString());
        to.setText(holder.ride.getLatPick().toString());
        price.setText(holder.ride.getPrice());


        Button button=holder.itemView.findViewById(R.id.request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRideDetails =new Intent(view.getContext(),RideDetails.class);
                goToRideDetails.putExtra("rideId",holder.ride.getId());
                view.getContext().startActivity(goToRideDetails);

            }
        });


    }

    @Override
    public int getItemCount() {
        return allRide.size();
    }
}
