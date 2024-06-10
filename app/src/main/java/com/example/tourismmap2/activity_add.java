package com.example.tourismmap2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ImageDecoderKt;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;

public class activity_add extends AppCompatActivity {

    ImageView addImage;
    Button saveButton;
    EditText addLocation, addLvl, addDuration, addLength, addDescription, addThread;

    Uri uri;
    String imageURL;
    //ArrayList<Uri> imageUris = new ArrayList<>();
   // ArrayList<String> imageUrls = new ArrayList<>();
   // StorageReference storageReference;
   // AlertDialog dialog;
   // RecyclerView recyclerView;
    //imageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addImage = findViewById(R.id.addImage);
        addLocation = findViewById(R.id.addLocation);
        addLvl = findViewById(R.id.addLvl);
        addDuration = findViewById(R.id.addDuration);
        addLength = findViewById(R.id.addLength);
        addDescription = findViewById(R.id.addDescription);
        addThread = findViewById(R.id.addThread);
        saveButton = findViewById(R.id.saveButton);



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(

                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            addImage.setImageURI(uri);
                        }
                        else{
                            Toast.makeText(activity_add.this, "No Image Selected", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );

        addImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveData();

            }
        });
    }

    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance("gs://tourismmap-24b45.appspot.com").getReference().child("Android images2").child(Objects.requireNonNull(uri.getLastPathSegment()));

        AlertDialog.Builder builder = new AlertDialog.Builder(activity_add.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                addData();
                dialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }

        });
    }

    public void addData(){
        String location = addLocation.getText().toString();
        String lvl = addLvl.getText().toString();
        String duration = addDuration.getText().toString();
        String length = addLength.getText().toString();
        String description = addDescription.getText().toString();
        String thread = addThread.getText().toString();

        // Получение идентификатора текущего пользователя
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userUID = user.getUid();

            // Создание объекта маршрута с добавлением userUID
            routeData routeData = new routeData(imageURL, location, lvl, duration, length, description, thread);

            FirebaseDatabase.getInstance("https://tourismmap-24b45-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference("TourismMap Data").child(location)
                    .setValue(routeData)
                    .addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public void onComplete(@NonNull Task<Void> task){
                            if (task.isSuccessful()){
                                Toast.makeText(activity_add.this, "Маршрут сохранен", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activity_add.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Log.e("activity_add", "Пользователь не аутентифицирован");
        }
    }
}


