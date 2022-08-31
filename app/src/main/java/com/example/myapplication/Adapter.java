package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    Context context;
    ArrayList<songs> list;
    View mview;


    public Adapter(Context context, ArrayList<songs> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemrecycle,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
      songs song = list.get(position);
      holder.artistName.setText(song.getArtistName());
      holder.songName.setText(song.getSongName());
        Glide.with(context).load(song.getImage()).into(holder.songImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView artistName, songName;
        ImageView songImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;

            artistName = mview.findViewById(R.id.Artis_name);
            songName = mview.findViewById(R.id.song_name);
            songImage = mview.findViewById(R.id.song_image);

        }
    }
}
