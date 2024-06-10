package com.example.tourismmap2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

public class ListHist extends AppCompatActivity {

    ImageView imageRouteHist;
    TextView textLocationHist, textLvlHist, textLengthHist, textDurationHist, textDescriptionHist, textThreadHist;
   // Button buttonAddHist;
   // DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hist);

        imageRouteHist = findViewById(R.id.imageRouteHist);
        textLocationHist = findViewById(R.id.textLocationHist);
        textLvlHist = findViewById(R.id.textLvlHist);
        textLengthHist = findViewById(R.id.textLengthHist);
        textDurationHist = findViewById(R.id.textDurationHist);
        textDescriptionHist = findViewById(R.id.textDescriptionHist);
        textThreadHist = findViewById(R.id.textThreadHist);


    Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            textLocationHist.setText(bundle.getString("location"));
            textLvlHist.setText(bundle.getString("lvl"));
            textLengthHist.setText(bundle.getString("length"));
            textDurationHist.setText(bundle.getString("duration"));
            textDescriptionHist.setText(bundle.getString("description"));
            textThreadHist.setText(bundle.getString("thread"));

        Glide.with(this).load(bundle.getString("image")).into(imageRouteHist);
    }}
}