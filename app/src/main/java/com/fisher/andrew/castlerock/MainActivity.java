package com.fisher.andrew.castlerock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.fisher.andrew.castlerock.StringConstants.API_URL;
import static com.fisher.andrew.castlerock.StringConstants.DISPLAYING_MESSAGE;
import static com.fisher.andrew.castlerock.StringConstants.DISPLAYING_NO_LINES;
import static com.fisher.andrew.castlerock.StringConstants.JSON_PARAMETER_DISPLAY;
import static com.fisher.andrew.castlerock.StringConstants.JSON_PARAMETER_LAST_UPDATED;
import static com.fisher.andrew.castlerock.StringConstants.JSON_PARAMETER_LINES;
import static com.fisher.andrew.castlerock.StringConstants.JSON_PARAMETER_NAME;
import static com.fisher.andrew.castlerock.StringConstants.JSON_PARAMETER_PAGES;
import static com.fisher.andrew.castlerock.StringConstants.JSON_PARAMETER_STATUS;

public class MainActivity extends AppCompatActivity {
    private List<TrafficSign> mSignList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            setListView();

        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(view -> {
            //Resets view after a button click
            setListView();
        });

    }

    //Retrieves a List of all signs and attaches them to a RecyclerView by using an adapter
    //Because the AsyncTask is in this method which is run during each onCreate, rotating the screen will refresh the API call instead
    // of crashing the application
    public void setListView(){
        try {
            mSignList = new TrafficTask().execute().get();

            sortSignListByTimestamp(mSignList);

            TrafficSignAdapter adapter = new TrafficSignAdapter(mSignList);
            RecyclerView adapterRV = findViewById(R.id.trafficInfoRV);
            adapterRV.setAdapter(adapter);
            adapterRV.setLayoutManager(new LinearLayoutManager(this));


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Overrides Collection.sort, to take a list of TrafficSign objects and sort them by the value of the timestamp
    //in descending order as the higher numbers are the most recent epoch time.
    public void sortSignListByTimestamp(List<TrafficSign> signList){
        Collections.sort(signList, (firstSign, secondSign) -> {
            if(firstSign.getTimestamp() < secondSign.getTimestamp()){
                return 1;
            }else if(firstSign.getTimestamp() > secondSign.getTimestamp()){
                return -1;
            }else{
                return 0;
            }
        });
    }

    public static class TrafficTask extends AsyncTask<Void,Void,List<TrafficSign>>{

        @Override
        protected List<TrafficSign> doInBackground(Void... voids) {
            List<TrafficSign> allSigns = new ArrayList<>();
            OkHttpClient client = new OkHttpClient();

            //Creates a request by using the provided URL
            Request request = new Request.Builder().url(API_URL).build();

            Response response;
            String jsonString = null;
            try {
                //using the client a call is made using the request created above.
                response = client.newCall(request).execute();

                //retries a data from the API as one long JSON formatted string.
                jsonString = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                //parses the initial jsonString to a JSONArray object, in order to properly parse out needed data.
                JSONArray trafficJSONResponse = new JSONArray(jsonString);

                //Loops through every JSON object that is a direct child of the JSONArray.
                for(int index = 0; index < trafficJSONResponse.length(); index++){
                    //Retrieve JSON object from the JSONArray at the current index
                    JSONObject currentJSONObject = trafficJSONResponse.getJSONObject(index);
                    //Parses through the JSON Object data and creates a TrafficSign object with user specified data.
                    TrafficSign currentSign = parseJsonToSignList(currentJSONObject);
                    //Adds the new sign to a list of all signs.
                    allSigns.add(currentSign);
                }


            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }

            return allSigns;
        }

        private TrafficSign parseJsonToSignList(JSONObject jsonObject){
            try {
                //Extract name and timestamp from the current JSON Object
                String signName = jsonObject.getString(JSON_PARAMETER_NAME);
                long lastUpdatedTimestamp = jsonObject.getLong(JSON_PARAMETER_LAST_UPDATED);
                String signDisplay;
                //Checks is the status parameters is DISPLAYING MESSAGE or BLANK
                boolean isDisplayingMessage = jsonObject.getString(JSON_PARAMETER_STATUS).equals(DISPLAYING_MESSAGE);

                //If a message is displaying more JSON parameters exist that consists of a string array with all the sign messages.
                if(isDisplayingMessage){

                    //Retrieves an array of all the signs associated with the current object
                    JSONArray pagesJSONArray = jsonObject.getJSONObject(JSON_PARAMETER_DISPLAY).getJSONArray(JSON_PARAMETER_PAGES);
                    StringBuilder messageBuilder = new StringBuilder();

                    //loops through all the signs associated with the current object, which are string arrays
                    for(int i = 0; i<pagesJSONArray.length();i++){
                        JSONObject currentPage = pagesJSONArray.getJSONObject(i);
                        JSONArray linesJSONArray = currentPage.getJSONArray(JSON_PARAMETER_LINES);

                        //Loops through the current signs message, which are stored as a string array
                        for(int j = 0; j < linesJSONArray.length();j++){
                            //gets current line in the sign message string array and appends it to a string builder
                            String currentLine = linesJSONArray.getString(j);
                            messageBuilder.append(linesJSONArray.getString(j));

                            //Adds a space to the end of a word if it is at the end of a line.
                            if(!currentLine.endsWith(" ")){
                                messageBuilder.append(" ");
                            }
                        }

                    }

                    //Take the string builder convert to string and set as the sign display
                    signDisplay = messageBuilder.toString();
                }else{
                    //creates a default message
                    signDisplay = DISPLAYING_NO_LINES;
                }

                //using the sign name, timestamp and message, create a TrafficSign object
                return new TrafficSign(signName,isDisplayingMessage,lastUpdatedTimestamp,signDisplay);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
