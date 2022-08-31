package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Home_page extends AppCompatActivity {
     RecyclerView mrecyclerview;
     FirebaseDatabase database;
     DatabaseReference reference;
     ArrayList<songs> list;
     Adapter adapter;
     Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mrecyclerview = findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("songs");
        mrecyclerview.setLayoutManager(new LinearLayoutManager(mcontext));
        mrecyclerview.setAdapter(adapter);
        adapter = new Adapter(mcontext,list);

        list = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    songs song =new songs();
                    song.setSongName(Objects.requireNonNull(dataSnapshot.child("SongName").getValue()).toString());
                    song.setArtistName(Objects.requireNonNull(dataSnapshot.child("ArtistName").getValue()).toString());
                    song.setImage(Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString());
                    list.add(song);
                }

                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}