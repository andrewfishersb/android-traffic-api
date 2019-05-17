package com.fisher.andrew.castlerock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.fisher.andrew.castlerock.StringConstants.INTENT_KEY_MESSAGE;

public class SignInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_information);

        //Retrieves clicked on signs message as a string and sets equal to a single text view.
        Intent intent = getIntent();
        String displayMessages = intent.getStringExtra(INTENT_KEY_MESSAGE);

        TextView displayMessageTV = findViewById(R.id.signDisplayMessageTv);
        displayMessageTV.setText(displayMessages);

    }
}
