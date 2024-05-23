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

public class RouteAdapters extends RecyclerView.Adapter<RouteAdapters.viewHolder> {

    private  List<routeData> rlist;
    private Context context;

    public RouteAdapters(Context context, List<routeData> rlist) {
        this.rlist = rlist;
        this.context = context;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView  duration, rank, length, location, lvl;
        CardView cardLayout;

        public viewHolder(@NonNull View itemView){
            super(itemView);


            image = itemView.findViewById(R.id.routeimage);
            location = itemView.findViewById(R.id.routelocation);
            rank = itemView.findViewById(R.id.routerank);
            length = itemView.findViewById(R.id.routelength);
            duration = itemView.findViewById(R.id.routeduration);
            lvl = itemView.findViewById(R.id.routelvl);

            cardLayout = itemView.findViewById(R.id.cardLayout);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        /*final routeitem items = rlist.get(position);
        holder.rimage.setImageResource(items.getImage());
        holder.location.setText(items.getLocation());
        holder.rank.setText(items.getRank());
        holder.duration.setText(items.getDuration());
        holder.length.setText(items.getLength());
        holder.lvl.setText(items.getLvl());*/

        Glide.with(context).load(rlist.get(position).getImage()).into(holder.image);
        holder.location.setText(rlist.get(position).getLocation());
        holder.lvl.setText(rlist.get(position).getLvl());
        holder.duration.setText(rlist.get(position).getDuration());
        holder.length.setText(rlist.get(position).getLength());
        holder.rank.setText(rlist.get(position).getRank());



        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListRoute1.class);

                intent.putExtra("image", rlist.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("location", rlist.get(holder.getAdapterPosition()).getLocation());
                intent.putExtra("lvl", rlist.get(holder.getAdapterPosition()).getLvl());
                intent.putExtra("duration", rlist.get(holder.getAdapterPosition()).getDuration());
                intent.putExtra("length", rlist.get(holder.getAdapterPosition()).getLength());
                intent.putExtra("rank", rlist.get(holder.getAdapterPosition()).getRank());
                intent.putExtra("description", rlist.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("thread", rlist.get(holder.getAdapterPosition()).getThread());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rlist.size();
    }

    public void searchRouteList(ArrayList<routeData> seachList){
        rlist = seachList;
        notifyDataSetChanged();
    }

}

