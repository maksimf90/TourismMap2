package com.example.tourismmap2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_help);

        TextView textName = findViewById(R.id.textName);
        TextView textDescHelp = findViewById(R.id.textDescHelp);

        textName.setText(getIntent().getStringExtra("nameHelp"));
        textDescHelp.setText(getIntent().getStringExtra("descriptionHelp"));

    }
}