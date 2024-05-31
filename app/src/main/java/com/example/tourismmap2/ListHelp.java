package com.example.tourismmap2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class ListHelp extends AppCompatActivity {

    //ImageView helpimage;
    TextView textnameHelp, textdescriptionHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_help);

        textnameHelp = findViewById(R.id.textnameHelp);
       textdescriptionHelp = findViewById(R.id.textdescriptionHelp);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            textnameHelp.setText(bundle.getString("nameHelp"));
            textdescriptionHelp.setText(bundle.getString("descriptionHelp"));

           //Glide.with(this).load(bundle.getString("image")).into(helpimage);
        }
    }
}