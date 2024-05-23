package com.example.tourismmap2;

import static com.google.android.material.internal.ViewUtils.getChildren;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_list extends AppCompatActivity {
//ArrayList<routeData>rlist;
RouteAdapters adapter;
RecyclerView listcard;
DatabaseReference databaseReference;
ValueEventListener eventListener;
List<routeData> rlist;
SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listcard = findViewById(R.id.listcard);
        searchView = findViewById(R.id.searchroute);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity_list.this, 2);
        listcard.setLayoutManager(gridLayoutManager);

       AlertDialog.Builder builder = new AlertDialog.Builder(activity_list.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();


        rlist = new ArrayList<>();

         adapter = new RouteAdapters(activity_list.this, rlist);
        listcard.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance("https://tourismmap-24b45-default-rtdb.europe-west1.firebasedatabase.app").getReference("TourismMap Data");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rlist.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    routeData routedata = itemSnapshot.getValue(routeData.class);
                    rlist.add(routedata);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              dialog.dismiss();
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }
    public void searchList(String text){
        ArrayList<routeData> searchList = new ArrayList<>();
        for (routeData rData: rlist){
            if (rData.getLocation().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(rData);
            }
        }
        adapter.searchRouteList(searchList);
    }
}

