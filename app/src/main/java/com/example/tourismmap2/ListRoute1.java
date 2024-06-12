package com.example.tourismmap2;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ListRoute1 extends AppCompatActivity {

    private static final String TAG = "ListRoute1";

    ImageView imageRoute1;
    TextView textLocation, textLvl, textLength, textDuration, textDescription, textThread;
    Button buttonAddHist;
    DatabaseReference databaseReference;
    Button buttonDownloadRoute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_route1);

        // Инициализация всех View
        imageRoute1 = findViewById(R.id.imageRoute1);
        textLocation = findViewById(R.id.textLocation);
        textLvl = findViewById(R.id.textLvl);
        textLength = findViewById(R.id.textLength);
        textDuration = findViewById(R.id.textDuration);
        textDescription = findViewById(R.id.textDescription);
        textThread = findViewById(R.id.textThread);
        buttonAddHist = findViewById(R.id.buttonAddHist);
        buttonDownloadRoute = findViewById(R.id.buttonDownloadRoute);

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
                Log.d(TAG, "Кнопка добавить в историю");
                addRouteToHistory();
            }
        });

        buttonDownloadRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Кнопка скачать");
                downloadImageAndData();
            }
        });
    }

    private void downloadImageAndData() {
        final String imageUrl = getIntent().getExtras().getString("image");
        final String location = textLocation.getText().toString();
        final String lvl = textLvl.getText().toString();
        final String length = textLength.getText().toString();
        final String duration = textDuration.getText().toString();
        final String description = textDescription.getText().toString();
        final String thread = textThread.getText().toString();

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        saveImageToExternalStorage(resource);
                        saveDataToExternalStorage(location, lvl, length, duration, description, thread);
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Toast.makeText(ListRoute1.this, "Ошибка в скачивании фотографии", Toast.LENGTH_SHORT).show();
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
                        Log.e(TAG, "Ошибка в добавлении маршрута в историю: " + databaseError.getMessage());
                        Toast.makeText(ListRoute1.this, "Ошибка в сохранении маршрута", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d(TAG, "Маршрут добавлен к пользователю с id: " + userId);
                        Toast.makeText(ListRoute1.this, "Маршрут сохранен", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } /*else {
            Log.e(TAG, "Failed to get a new route ID from Firebase");
        }*/
    }

    private void downloadImage() {
        final String imageUrl = getIntent().getExtras().getString("image");
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        saveImageToExternalStorage(resource);
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Toast.makeText(ListRoute1.this, "Ошибка в сохранении фотографии", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveImageToExternalStorage(Bitmap bitmap) {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "TourismMap");
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            Log.e(TAG, "Не удалось создать каталог: " + storageDir.getPath());
            return;
        }

        String fileName = System.currentTimeMillis() + ".jpg";
        File imageFile = new File(storageDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Toast.makeText(this, "Фотография сохранена: " + imageFile.getPath(), Toast.LENGTH_LONG).show();
            Log.d(TAG, "Фотография сохранена: " + imageFile.getPath());
        } catch (IOException e) {
            Log.e(TAG, "Ошибка в сохранении фотографии: " + e.getMessage(), e);
            Toast.makeText(this, "Ошибка в сохранении фотографии", Toast.LENGTH_SHORT).show();
        }
    }
    private void saveDataToExternalStorage(String location, String lvl, String length, String duration, String description, String thread) {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "TourismMap");
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            Log.e(TAG, "Не удалось создать каталог: " + storageDir.getPath());
            return;
        }

        String fileName = System.currentTimeMillis() + ".txt";
        File dataFile = new File(storageDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            String data = "Локация: " + location + "\n" +
                    "Уровень сложности: " + lvl + "\n" +
                    "Длина: " + length + "\n" +
                    "Длительность: " + duration + "\n" +
                    "Описание: " + description + "\n" +
                    "Нитка маршрута: " + thread + "\n";
            fos.write(data.getBytes());
            Toast.makeText(this, "Сохранено: " + dataFile.getPath(), Toast.LENGTH_LONG).show();
            Log.d(TAG, "Данные сохранены: " + dataFile.getPath());
        } catch (IOException e) {
            Log.e(TAG, "Ошибка в сохранении данных: " + e.getMessage(), e);
            Toast.makeText(this, "Ошибка в сохранении", Toast.LENGTH_SHORT).show();
        }
    }
}
