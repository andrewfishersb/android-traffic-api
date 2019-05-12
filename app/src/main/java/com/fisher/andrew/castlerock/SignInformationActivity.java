package com.fisher.andrew.castlerock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SignInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_information);

        Intent intent = getIntent();

        String displayMessages = intent.getStringExtra("display_message");
        TextView displayMessageTV = findViewById(R.id.signDisplayMessageTv);
        displayMessageTV.setText(displayMessages);

    }
}
