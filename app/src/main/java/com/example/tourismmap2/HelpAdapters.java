package com.example.tourismmap2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelpAdapters extends RecyclerView.Adapter<HelpAdapters.viewHolder> {

    ArrayList<helpitem> hlist;
    Context context;

    public HelpAdapters(ArrayList<helpitem> hlist, Context context) {
        this.hlist = hlist;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.help_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,  int position) {
        final helpitem items = hlist.get(position);
        holder.himage.setImageResource(items.getImage());
        holder.nameHelp.setText(items.getNameHelp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListHelp.class);

                intent.putExtra("himage", hlist.get(position).getImage());
                intent.putExtra("nameHelp", hlist.get(position).getNameHelp());
                intent.putExtra("descriptionHelp", hlist.get(position).getDescriptionHelp());


                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return hlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView himage;
        TextView nameHelp;
        public viewHolder(@NonNull View itemView){
            super(itemView);


            himage = itemView.findViewById(R.id.helpimage);
            nameHelp = itemView.findViewById(R.id.helpname);
        }
    }
}




