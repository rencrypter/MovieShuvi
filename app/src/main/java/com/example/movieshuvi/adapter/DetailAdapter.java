package com.example.movieshuvi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieshuvi.R;
import com.example.movieshuvi.model.ChildModel;
import com.example.movieshuvi.model.DetailModel;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    List<DetailModel> dList;
    Context context;
    public DetailAdapter(List<DetailModel> dList, Context context) {
        this.dList = dList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_items, null, false);
        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailModel model = dList.get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + model.getPoster_path())
                .into(holder.iv_child_image);
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image = itemView.findViewById(R.id.iv_child_item);
        }
    }
}
