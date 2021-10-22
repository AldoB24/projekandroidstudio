package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button btncalculator, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        btncalculator = findViewById(R.id.app_calculator);
        btnLogout = findViewById(R.id.logout);
        btncalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculator = getPackageManager().getLaunchIntentForPackage
                        ("com.miui.calculator");
                startActivity(calculator);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save Username
                mEditor.putString(getString(R.string.username), "");
                mEditor.commit();

                // Save Has Login
                mEditor.putString(getString(R.string.login), "false");
                mEditor.commit();

                Intent home = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
    }

    private void checkLogin() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String hasLogin = mPreferences.getString(getString(R.string.login), "false");

        if(hasLogin.equals("false")) {
            Intent home = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(home);
        }
    }
}