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

public class ListRoute1 extends AppCompatActivity {

    ImageView imageRoute1;
    TextView textLocation, textLvl, textLength, textDuration, textDescription, textThread;
    Button buttonAddHist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_route1);

         imageRoute1 = findViewById(R.id.imageRoute1);
         textLocation = findViewById(R.id.textLocation);
         textLvl = findViewById(R.id.textLvl);
         textLength = findViewById(R.id.textLength);
         textDuration = findViewById(R.id.textDuration);
         textDescription = findViewById(R.id.textDescription);
         textThread = findViewById(R.id.textThread);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            textLocation.setText(bundle.getString("location"));
            textLvl.setText(bundle.getString("lvl"));
            textLength.setText(bundle.getString("length"));
            textDuration.setText(bundle.getString("duration"));
            textDescription.setText(bundle.getString("description"));
            textThread.setText(bundle.getString("thread"));

            Glide.with(this).load(bundle.getString("image")).into(imageRoute1);

        }
    }
}