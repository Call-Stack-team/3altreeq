package com.example.a3altareeq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRidesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRidesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "driverName";
    private static final String ARG_PARAM2 = "dateTime";
    private static final String ARG_PARAM3 = "numberOfSeats";
    private static final String ARG_PARAM4 = "price";
    private static final String ARG_PARAM5 = "latDrop";
    private static final String ARG_PARAM6 = "latPick";
    private static final String ARG_PARAM7 = "lonDrop";
    private static final String ARG_PARAM8 = "lonPick";

    // TODO: Rename and change types of parameters
    private String mDriverName;
    private String mDateTime;
    private Integer mNumberOfSeats;
    private String mPrice;
    private Float mLatDrop;
    private Float mLatPick;
    private Float mLonDrop;
    private Float mLonPick;

    public UserRidesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mDriverName Parameter 1.
     * @param mDateTime Parameter 2.
     * @param mNumberOfSeats Parameter 3.
     * @param mPrice Parameter 4.
     * @param mLatDrop Parameter 5.
     * @param mLatPick Parameter 6.
     * @param mLonDrop Parameter 7.
     * @param mLonPick Parameter 8.
     * @return A new instance of fragment UserRidesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserRidesFragment newInstance(String mDriverName, String mDateTime,Integer mNumberOfSeats,String mPrice,Float mLatDrop,Float mLatPick,Float mLonDrop,Float mLonPick ) {
        UserRidesFragment fragment = new UserRidesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mDriverName);
        args.putString(ARG_PARAM2, mDateTime);
        args.putString(ARG_PARAM3, mNumberOfSeats.toString());
        args.putString(ARG_PARAM4, mPrice);
        args.putString(ARG_PARAM5, mLatDrop.toString());
        args.putString(ARG_PARAM6, mLatPick.toString());
        args.putString(ARG_PARAM7, mLonDrop.toString());
        args.putString(ARG_PARAM8, mLonPick.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDriverName =  getArguments().getString(ARG_PARAM1);
            mDateTime = getArguments().getString(ARG_PARAM2);
            mNumberOfSeats =Integer.parseInt( getArguments().getString(ARG_PARAM3));
            mPrice = getArguments().getString(ARG_PARAM4);
            mLatDrop =Float.parseFloat(getArguments().getString(ARG_PARAM5));
            mLatPick =Float.parseFloat( getArguments().getString(ARG_PARAM6));
            mLonDrop =Float.parseFloat(getArguments().getString(ARG_PARAM7));
            mLonPick =Float.parseFloat(getArguments().getString(ARG_PARAM8));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_rides, container, false);
    }
}