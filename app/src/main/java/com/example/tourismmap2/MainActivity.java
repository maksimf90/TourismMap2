package com.example.tourismmap2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void historyClick (View v){
        Intent intent = new Intent(this, activity_history.class);
        startActivity(intent);
    }

    public void listClick (View v){
        Intent intent = new Intent(this, activity_list.class);
        startActivity(intent);
    }

    public void helpClick (View v){
        Intent intent = new Intent(this, activity_help.class);
        startActivity(intent);
    }

    public void addClick (View v){
        Intent intent = new Intent(this, activity_add.class);
        startActivity(intent);
    }
}