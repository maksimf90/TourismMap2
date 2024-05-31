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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapters extends RecyclerView.Adapter<HelpAdapters.viewHolder> {

    private List<helpData> hlist;
    private Context context;



    public HelpAdapters(Context context, List<helpData> hlist) {
        this.hlist = hlist;
        this.context = context;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nameHelp;
        CardView cardLayoutHelp;

        public viewHolder(@NonNull View itemView){
            super(itemView);


            image = itemView.findViewById(R.id.helpimage);
            nameHelp = itemView.findViewById(R.id.nameHelp);

            cardLayoutHelp = itemView.findViewById(R.id.cardLayoutHelp);

        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.help_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,  int position) {
        /*final helpitem items = hlist.get(position);
        holder.himage.setImageResource(items.getImage());
        holder.nameHelp.setText(items.getNameHelp());*/

        Glide.with(context).load(hlist.get(position).getImage()).into(holder.image);
        holder.nameHelp.setText(hlist.get(position).getNameHelp());


        holder.cardLayoutHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListHelp.class);

               /* intent.putExtra("himage", hlist.get(position).getImage());
                intent.putExtra("nameHelp", hlist.get(position).getNameHelp());
                intent.putExtra("descriptionHelp", hlist.get(position).getDescriptionHelp());*/

                intent.putExtra("image", hlist.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("nameHelp", hlist.get(holder.getAdapterPosition()).getNameHelp());
                intent.putExtra("descriptionHelp", hlist.get(holder.getAdapterPosition()).getDescriptionHelp());

                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return hlist.size();
    }

    public void searchHelpList(ArrayList<helpData> helpList){
        hlist = helpList;
        notifyDataSetChanged();
    }
}




