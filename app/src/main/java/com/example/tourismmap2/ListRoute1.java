package com.example.tourismmap2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class ListRoute1 extends AppCompatActivity {

    private static final String TAG = "ListRoute1";

    ImageView imageRoute1;
    TextView textLocation, textLvl, textLength, textDuration, textDescription, textThread;
    Button buttonAddHist;
    DatabaseReference databaseReference;

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
        buttonAddHist = findViewById(R.id.buttonAddHist);

        // Инициализация Firebase Database
        databaseReference = FirebaseDatabase.getInstance("https://tourismmap-24b45-default-rtdb.europe-west1.firebasedatabase.app").getReference("TourismMap History");

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

        buttonAddHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Add to history button clicked");
                addRouteToHistory();
            }
        });
    }

    private void addRouteToHistory() {
        String location = textLocation.getText().toString();
        String lvl = textLvl.getText().toString();
        String length = textLength.getText().toString();
        String duration = textDuration.getText().toString();
        String description = textDescription.getText().toString();
        String thread = textThread.getText().toString();
        String image = getIntent().getExtras().getString("image");
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String routeId = databaseReference.child(userId).push().getKey();

        // Создание объекта маршрута
        Route routeD = new Route(location, lvl, length, duration, description, thread, image);

        // Добавление маршрута в базу данных Firebase
        if (routeId != null) {
            databaseReference.child(userId).child(routeId).setValue(routeD, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Log.e(TAG, "Failed to add route to history: " + databaseError.getMessage());
                        Toast.makeText(ListRoute1.this, "Ошибка в сохранении маршрута", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d(TAG, "Route successfully added to history for user: " + userId);
                        Toast.makeText(ListRoute1.this, "Маршрут сохранен", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } else {
            Log.e(TAG, "Failed to get a new route ID from Firebase");
        }
    }
}
