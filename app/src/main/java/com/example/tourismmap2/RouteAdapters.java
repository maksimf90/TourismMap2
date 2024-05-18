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

public class RouteAdapters extends RecyclerView.Adapter<RouteAdapters.viewHolder> {

    ArrayList<routeitem>rlist;
    Context context;

    public RouteAdapters(ArrayList<routeitem> rlist, Context context) {
        this.rlist = rlist;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.route_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final routeitem items = rlist.get(position);
        holder.rimage.setImageResource(items.getImage());
        holder.location.setText(items.getLocation());
        holder.rank.setText(items.getRank());
        holder.duration.setText(items.getDuration());
        holder.length.setText(items.getLength());
        holder.lvl.setText(items.getLvl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListRoute1.class);

                intent.putExtra("rimage", rlist.get(position).getImage());
                intent.putExtra("duration", rlist.get(position).getDuration());
                intent.putExtra("lvl", rlist.get(position).getLvl());
                intent.putExtra("length", rlist.get(position).getLength());
                intent.putExtra("rank", rlist.get(position).getRank());
                intent.putExtra("location", rlist.get(position).getLocation());
                intent.putExtra("description", rlist.get(position).getDescription());
                intent.putExtra("thread", rlist.get(position).getThread());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView rimage;
        TextView  duration, rank, length, location, lvl;
        public viewHolder(@NonNull View itemView){
        super(itemView);


        rimage = itemView.findViewById(R.id.routeimage);
        location = itemView.findViewById(R.id.routelocation);
        rank = itemView.findViewById(R.id.routerank);
        length = itemView.findViewById(R.id.routelength);
        duration = itemView.findViewById(R.id.routeduration);
        lvl = itemView.findViewById(R.id.routelvl);
    }
    }
}
