package com.hsf.hsftest.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hsf.hsftest.R;

public class MapsActivity extends FragmentActivity {
    private GMapFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        fragment = (GMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.help_content);
    }
}
