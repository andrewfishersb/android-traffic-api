package com.fisher.andrew.castlerock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_URL = "https://iatg.carsprogram.org/signs_v1/api/signs";
    private RecyclerView mAdapterRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TrafficSign> signList = new ArrayList<>();


        TrafficSignAdapter adapter = new TrafficSignAdapter(signList);
        mAdapterRV = findViewById(R.id.trafficInfoRV);
        mAdapterRV.setAdapter(adapter);
        mAdapterRV.setLayoutManager(new LinearLayoutManager(this));
        new TrafficTask().execute();
    }

    public static class TrafficTask extends AsyncTask<Void,Void,List<TrafficSign>>{

        @Override
        protected List<TrafficSign> doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(API_URL).build();

            Response response;
            String jsonString = null;
            try {
                response = client.newCall(request).execute();
                jsonString = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
            JSONArray trafficJSONResponse = new JSONArray(jsonString);

                JSONObject j1 = trafficJSONResponse.getJSONObject(0);
                Log.d("TestJSON","Last Updated: " + j1.getLong("lastUpdated"));
                Log.d("TestJSON","Status: " + j1.getString("status"));
                Log.d("TestJSON","Name: " + j1.getString("name"));







            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }

            return null;
        }

        public TrafficSign parseJsonToSign(JSONObject jsonObject){
            try {
                String signName = jsonObject.getString("name");
                boolean isDisplayingMessage = jsonObject.getString("status").equals("DISPLAYING_MESSAGE");
                long lastUpdatedTimestamp = jsonObject.getLong("lastUpdated");
                JSONArray displayArray = jsonObject.getJSONObject("display").getJSONArray("pages");


                for(int i = 0; i<displayArray.length();i++){









                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
