package com.example.sportmeet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class activity_deconnexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deconnexion);
    }


    public void logout(View view) {

        FirebaseAuth.getInstance().signOut(); // déconnexion
        startActivity(new Intent(getApplicationContext(),LoginActivity.class ));
        finish();

    }
}