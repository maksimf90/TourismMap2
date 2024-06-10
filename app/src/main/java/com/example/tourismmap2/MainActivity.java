package com.example.tourismmap2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String uid; // Объявление переменной uid как члена класса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        uid = intent.getStringExtra("USER_UID"); // Присваивание значения переменной uid

        if (uid != null) {
            // Используйте UID для получения данных пользователя
        }

    }

    public void historyClick (View v){
        Intent intent = new Intent(MainActivity.this, activity_history.class);
        intent.putExtra("USER_UID", uid); // uid - ваш userUID
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
