package com.fisher.andrew.castlerock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String API_URL = "https://iatg.carsprogram.org/signs_v1/api/signs";
    private RecyclerView mAdapterRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TrafficSign> signList = new ArrayList<>();


        TrafficSignAdapter adapter = new TrafficSignAdapter(signTest);
        mAdapterRV = findViewById(R.id.trafficInfoRV);
        mAdapterRV.setAdapter(adapter);
        mAdapterRV.setLayoutManager(new LinearLayoutManager(this));

    }
}
