package com.example.a3altareeq;


import android.content.Intent;
import android.net.Uri;
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
import com.amplifyframework.datastore.generated.model.RideUser;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengersViewHolder>{
    private List<User> AllPassenger=new ArrayList<User>();

    public PassengerAdapter(List<User> AllPassenger) {
        this.AllPassenger = AllPassenger;
    }
    public static class PassengersViewHolder extends RecyclerView.ViewHolder{
        public User user;
        public View itemView;

        public PassengersViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }
    @NonNull
    @Override
    public PassengersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_passengers,parent,false);
        PassengerAdapter.PassengersViewHolder passengersViewHolder =new PassengerAdapter.PassengersViewHolder(view);
        return passengersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PassengersViewHolder holder, int position) {
        holder.user=AllPassenger.get(position);

        TextView passengerFirstName = holder.itemView.findViewById(R.id.firstNameInPassFrag);
        TextView passengerLastName = holder.itemView.findViewById(R.id.lastNamePassengerFragment);
        TextView passengerPhoneNumber = holder.itemView.findViewById(R.id.phoneNumberPassengerFragment);
        TextView pickUP=holder.itemView.findViewById(R.id.pickUpPassengerFragment);
        TextView dropOff=holder.itemView.findViewById(R.id.dropOffPassengerFragment);
        Button deletePassenger=holder.itemView.findViewById(R.id.deletePassenger);
        passengerFirstName.setText(holder.user.getFirstName());
        passengerLastName.setText(holder.user.getLastName());
        passengerPhoneNumber.setText(holder.user.getPhoneNumber());

        pickUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", holder.user.getPickupUserlat(),holder.user.getPickupUserlon());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                view.getContext().startActivity(intent);
            }
        });

        dropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", holder.user.getDropUserlat(),holder.user.getDropUserlon());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                view.getContext().startActivity(intent);
            }
        });

        deletePassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return AllPassenger.size() ;
    }

    public List<User> getAllPassenger() {
        return AllPassenger;
    }

    public void setAllPassenger(List<User> allPassenger) {
        AllPassenger = allPassenger;
    }
}
