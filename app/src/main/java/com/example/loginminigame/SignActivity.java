package com.example.loginminigame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SignActivity extends AppCompatActivity {

    Button confirmButton, backButton;
    EditText mailEditText, passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        mailEditText = findViewById(R.id.editTexTEmail);
        passEditText = findViewById(R.id.editTextPassword);

        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view -> {

            boolean access = checkSignIn(mailEditText.getText().toString(), passEditText.getText().toString());
            if(access) {
                saveUser(mailEditText.getText().toString(), passEditText.getText().toString());
                startActivity(new Intent(SignActivity.this, LoginActivity.class));
                finish();
            } else
                Toast.makeText(this , getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(SignActivity.this, MainActivity.class));
            finish();
        });
    }

    public boolean checkSignIn(String email, String password) {
        if (email == null || email.isEmpty()) return false;
        if (!email.contains("@")) return false;
        if (password == null || password.isEmpty()) return false;

        return true;
    }

    public void saveUser (String email, String password) {
        String userKey = email.substring(0, email.indexOf("@"));

        SharedPreferences.Editor editor = getSharedPreferences(userKey, MODE_PRIVATE).edit();
        editor.putString("password", password);
        editor.commit();


    }
}