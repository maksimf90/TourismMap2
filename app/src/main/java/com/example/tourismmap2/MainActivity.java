package com.example.tourismmap2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView phraseTextView;
    private String[] phrases = {
            "Добро пожаловать!",
            "Приятного путешествия!",
            "Откройте для себя новое!",
            "Каждый день - новое приключение!",
            "Исследуйте мир!"
    };
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        uid = intent.getStringExtra("USER_UID");

        if (uid != null) {
        }
        phraseTextView = findViewById(R.id.phraseTextView);
        updatePhrase();
    }

    private void updatePhrase() {
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        phraseTextView.setText(phrases[index]);
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
