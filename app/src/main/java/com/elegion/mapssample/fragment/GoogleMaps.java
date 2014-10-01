package com.elegion.mapssample.fragment;

import android.os.Bundle;

import com.elegion.mapssample.R;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author Daniel Serdyukov
 */
public class GoogleMaps extends MapFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMap().addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                .position(getActivity().getIntent()
                        .<LatLng>getParcelableExtra("location"))
                .title("ЛОМО"));
        getMap().setMyLocationEnabled(true);
    }

}
