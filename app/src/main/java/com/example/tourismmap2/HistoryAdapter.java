package com.example.tourismmap2;

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

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RouteViewHolder> {

    private List<Route> routeList;
    private Context context;

    public static class RouteViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public ImageView imageRoute;
        public CardView cardLayoutHis;

        public RouteViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.hisName);
            imageRoute = itemView.findViewById(R.id.hisImage);
            cardLayoutHis = itemView.findViewById(R.id.cardLayoutHis);
        }
    }

    public HistoryAdapter(Context context, List<Route> routeList) {
        this.context = context;
        this.routeList = routeList;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.his_layout, parent, false);
        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        Route route = routeList.get(position);
        holder.textName.setText(route.getLocation());
        Glide.with(context).load(route.getImage()).into(holder.imageRoute);

        holder.cardLayoutHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListHist.class);

                Route currentRoute = routeList.get(holder.getAdapterPosition());
                intent.putExtra("image", currentRoute.getImage());
                intent.putExtra("location", currentRoute.getLocation());
                intent.putExtra("lvl", currentRoute.getLvl());
                intent.putExtra("duration", currentRoute.getDuration());
                intent.putExtra("length", currentRoute.getLength());
                intent.putExtra("description", currentRoute.getDescription());
                intent.putExtra("thread", currentRoute.getThread());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }
}
