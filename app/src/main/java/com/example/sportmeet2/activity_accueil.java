package com.example.sportmeet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_accueil extends AppCompatActivity {
    private Button button;
    private Button button_inscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        button =(Button) findViewById(R.id.button3);
        button_inscription=(Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConnexion();
            }
        });
        button_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInscription();
            }
        });

    }

    private void openInscription() {
        Intent intent  =new Intent(this, activity_inscription.class);
        startActivity(intent);

    }

    public void openConnexion(){
        Intent intent  =new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}