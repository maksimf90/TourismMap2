package com.example.tourismmap2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListRoute1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_route1);

        ImageView routeimage = findViewById(R.id.imageRoute1);
        TextView routelocation = findViewById(R.id.textLocation);
        TextView routelvl = findViewById(R.id.textLvl);
        TextView routelength = findViewById(R.id.textLength);
        TextView routeduration = findViewById(R.id.textDuration);
        TextView routerank = findViewById(R.id.textRank);
        TextView routedescription = findViewById(R.id.textDescription);
        TextView routethread = findViewById(R.id.textThread);

       routeimage.setImageResource(getIntent().getIntExtra("rimage", 0));
       routelocation.setText(getIntent().getStringExtra("location"));
       routelvl.setText(getIntent().getStringExtra("lvl"));
       routelength.setText(getIntent().getStringExtra("length"));
       routerank.setText(getIntent().getStringExtra("rank"));
       routeduration.setText(getIntent().getStringExtra("duration"));
       routedescription.setText(getIntent().getStringExtra("description"));
       routethread.setText(getIntent().getStringExtra("thread"));
    }
}