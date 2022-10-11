package com.example.loginminigame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button signInButton, logInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent logIntent = new Intent(MainActivity.this, LoginActivity.class);

        logInButton = findViewById(R.id.loginButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(logIntent);
                finish();
            }
        });

        Intent signIntent = new Intent(MainActivity.this, SignActivity.class);

        logInButton = findViewById(R.id.signinButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signIntent);
                finish();
            }
        });
    }
}