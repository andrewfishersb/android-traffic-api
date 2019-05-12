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
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_URL = "https://iatg.carsprogram.org/signs_v1/api/signs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TrafficSign> signList = null;
        try {
            signList = new TrafficTask().execute().get();

            TrafficSignAdapter adapter = new TrafficSignAdapter(signList);
            RecyclerView adapterRV = findViewById(R.id.trafficInfoRV);
            adapterRV.setAdapter(adapter);
            adapterRV.setLayoutManager(new LinearLayoutManager(this));


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static class TrafficTask extends AsyncTask<Void,Void,List<TrafficSign>>{

        @Override
        protected List<TrafficSign> doInBackground(Void... voids) {
            List<TrafficSign> allSigns = new ArrayList<>();
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(API_URL).build();

            Response response;
            String jsonString = null;
            try {
                response = client.newCall(request).execute();
                jsonString = response.body().string();
                Log.d("JSONString",jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                JSONArray trafficJSONResponse = new JSONArray(jsonString);

//                for(int index = 0; index < trafficJSONResponse.length(); index++){
                for(int index = 0; index < trafficJSONResponse.length(); index++){
                    JSONObject currentJSONObject = trafficJSONResponse.getJSONObject(index);
                    TrafficSign currentSign = parseJsonToSignList(currentJSONObject);
                    allSigns.add(currentSign);
                }


            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }

            return allSigns;
        }

        public TrafficSign parseJsonToSignList(JSONObject jsonObject){
            try {
                String signName = jsonObject.getString("name");
                boolean isDisplayingMessage = jsonObject.getString("status").equals("DISPLAYING_MESSAGE");
                long lastUpdatedTimestamp = jsonObject.getLong("lastUpdated");
                TrafficSign currentParsedSign = new TrafficSign(signName,isDisplayingMessage,lastUpdatedTimestamp);

                if(isDisplayingMessage){

                    JSONArray pagesJSONArray = jsonObject.getJSONObject("display").getJSONArray("pages");

                    for(int i = 0; i<pagesJSONArray.length();i++){
                        StringBuilder messageBuilder = new StringBuilder();
                        JSONObject currentPage = pagesJSONArray.getJSONObject(i);
                        JSONArray linesJSONArray = currentPage.getJSONArray("lines");

                        for(int j = 0; j < linesJSONArray.length();j++){
                            messageBuilder.append(linesJSONArray.getString(j));
                        }

                        String trafficMessage = messageBuilder.toString();
                        currentParsedSign.addMessage(trafficMessage);

                    }



                }



                return currentParsedSign;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
