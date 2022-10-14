package com.example.loginminigame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button confirmButton, backButton;
    EditText mailEditText, passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mailEditText = findViewById(R.id.editTexTEmail2);
        passEditText = findViewById(R.id.editTextPassword2);

        confirmButton = findViewById(R.id.confirmButton2);
        confirmButton.setOnClickListener(view -> {
            boolean validLogin = userExists(mailEditText.getText().toString(), passEditText.getText().toString());
            String user = mailEditText.getText().toString().substring(0, mailEditText.getText().toString().indexOf("@"));

            if(validLogin) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }else
                Toast.makeText(this , getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        });

        backButton = findViewById(R.id.back_button2);
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    public boolean userExists(String email, String password) {
        String userKey = email.substring(0, email.indexOf("@"));

        try {
            SharedPreferences pref = getSharedPreferences(userKey, MODE_PRIVATE);
            if(pref.getAll().isEmpty()) return false;

            Map<String, ?> allEntries = pref.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                if (!entry.getValue().toString().equals(password)) return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}