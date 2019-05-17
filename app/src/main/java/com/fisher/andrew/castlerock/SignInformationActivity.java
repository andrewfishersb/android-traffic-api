package com.fisher.andrew.castlerock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.fisher.andrew.castlerock.StringConstants.PARCELABLE_KEY_TRAFFIC_SIGN_OBJECT;

public class SignInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_information);

        //Retrieves a traffic sign object on the clicked item in the adapter
        Intent intent = getIntent();
        TrafficSign currentTrafficSign = intent.getParcelableExtra(PARCELABLE_KEY_TRAFFIC_SIGN_OBJECT);

        //Retrieves and displays message from the current traffic sign
        String displayMessages = currentTrafficSign.getMessage();
        TextView displayMessageTV = findViewById(R.id.signDisplayMessageTv);
        displayMessageTV.setText(displayMessages);

    }
}
