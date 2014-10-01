package com.elegion.mapssample.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.elegion.mapssample.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * @author Daniel Serdyukov
 */
public class GeocodeActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText mAddress;

    private Button mGoButton;

    private ListView mAddressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_geocode);
        mAddress = (EditText) findViewById(android.R.id.text1);
        mGoButton = (Button) findViewById(android.R.id.button1);
        mAddressList = (ListView) findViewById(android.R.id.list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoButton.setOnClickListener(this);
        mAddressList.setOnItemClickListener(this);
    }

    @Override
    protected void onPause() {
        mGoButton.setOnClickListener(null);
        mAddressList.setOnItemClickListener(null);
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        if (Geocoder.isPresent()) {
            try {
                final List<Address> addressList = new Geocoder(this)
                        .getFromLocationName(mAddress.getText().toString(), 5);
                if (addressList != null) {
                    mAddressList.setAdapter(new ArrayAdapter<Address>(this,
                            android.R.layout.simple_list_item_1, addressList));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Геокодер недоступен", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Address address = (Address) parent.getAdapter().getItem(position);
        final Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("location", new LatLng(
                address.getLatitude(),
                address.getLongitude()
        ));
        startActivity(intent);
    }
}
