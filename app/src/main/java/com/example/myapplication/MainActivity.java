package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtNum;
    Button btnGreen, btnRed, btnBlue, btnYellow, btnCount,btnReset;
    int count = 0;
    Boolean saved;
    int backgroundColor=Color.CYAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNum =findViewById(R.id.Number);
        getButton();
        SharedPreferences mPreferences = getSharedPreferences(getString(R.string.shared_prefs_name), MODE_PRIVATE);

        if (savedInstanceState == null){
            count = mPreferences.getInt("count", count);
            backgroundColor=mPreferences.getInt("color",backgroundColor);
            txtNum.setText(String.valueOf(count));
            txtNum.setBackgroundColor(backgroundColor);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Setting.class);
        switch (item.getItemId()){
            case R.id.settingItem:
                intent.putExtra("setting", "setting");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        saved = sharedPreferences.getBoolean("remember_choice", false);
        System.out.println("Save your choice: "+saved);
        if(saved == true){
            SharedPreferences mPreferences = getSharedPreferences(getString(R.string.shared_prefs_name), MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt("count", count);
            preferencesEditor.putInt("color", backgroundColor);
            preferencesEditor.clear();
            preferencesEditor.apply();
        }
    }
    protected void getButton(){
        btnGreen = findViewById(R.id.btngreen);
        btnGreen.setTag(0);
        btnGreen.setOnClickListener(this);

        btnBlue = findViewById(R.id.btnBlue);
        btnBlue.setTag(1);
        btnBlue.setOnClickListener(this);

        btnRed =findViewById(R.id.btnRed);
        btnRed.setTag(2);
        btnRed.setOnClickListener(this);

        btnYellow =findViewById(R.id.btnYelow);
        btnYellow.setTag(3);
        btnYellow.setOnClickListener(this);

        btnCount=findViewById(R.id.btnCount);
        btnCount.setTag(4);
        btnCount.setOnClickListener(this);

        btnReset=findViewById(R.id.btnReset);
        btnReset.setTag(5);
        btnReset.setOnClickListener(this);

    }
    protected void clearValueAndColor(){
        count=0;
        txtNum.setText(String.valueOf(count));
        backgroundColor=getResources().getColor(R.color.love);
        txtNum.setBackgroundColor(backgroundColor);
    }


    @Override
    public void onClick(View view) {
        txtNum = findViewById(R.id.Number);
        int tag=Integer.parseInt(String.valueOf(view.getTag()));
        switch (tag) {
            case 0:
                backgroundColor = getResources().getColor(R.color.green);
                txtNum.setBackgroundColor(backgroundColor);
                break;
            case 1:
                backgroundColor = getResources().getColor(R.color.blue);
                txtNum.setBackgroundColor(backgroundColor);
                break;
            case 2:
                backgroundColor = getResources().getColor(R.color.red);
                txtNum.setBackgroundColor(backgroundColor);
                break;
            case 3:
                backgroundColor = getResources().getColor(R.color.yellow);
                txtNum.setBackgroundColor(backgroundColor);
                break;
            case 4:
                count++;
                txtNum.setText(String.valueOf(count));
                break;
            case 5:
                clearValueAndColor();

                break;
        }
    }
}