package com.example.WaGbA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.WaGbA.databinding.ActivityDishesBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
public class Dishes extends AppCompatActivity implements DishInterfaceClass  {
    private ActivityDishesBinding binding;
    private DishesAdapter adapter;
    private Intent receivePos;
    DishesClass dishesClass;
    String price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDishesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //img=findViewById(R.id.img1);
        binding.recview.setLayoutManager(new LinearLayoutManager(this));
        receivePos=getIntent();
        int position=receivePos.getIntExtra("pos",0);
        switch (position){
            case 0:
            FirebaseRecyclerOptions<DishesClass>kfc =
                    new FirebaseRecyclerOptions.Builder<DishesClass>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("KFC"), DishesClass.class)
                            .build();
            adapter = new DishesAdapter(kfc, this);
            binding.recview.setAdapter(adapter);
            break;

            case 1:
                FirebaseRecyclerOptions<DishesClass>kbb =
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("kabab"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(kbb, (DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 2:
                FirebaseRecyclerOptions<DishesClass>mc =
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Mc"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(mc, (DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 3:
                FirebaseRecyclerOptions<DishesClass>pg=
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("pergo"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(pg,(DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 4:
                FirebaseRecyclerOptions<DishesClass>bk =
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("bkry"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(bk,(DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 5:
                FirebaseRecyclerOptions<DishesClass>co =
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("coash"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(co, (DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 6:
                FirebaseRecyclerOptions<DishesClass>spicy=
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Spicy"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(spicy, (DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 7:
                FirebaseRecyclerOptions<DishesClass>ind =
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("hindi"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(ind, (DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;


            case 8:
                FirebaseRecyclerOptions<DishesClass>tom=
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Koshry"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(tom, (DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

            case 9:
                FirebaseRecyclerOptions<DishesClass>fsh=
                        new FirebaseRecyclerOptions.Builder<DishesClass>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("fish"), DishesClass.class)
                                .build();
                adapter = new DishesAdapter(fsh,(DishInterfaceClass) this);
                binding.recview.setAdapter(adapter);
                break;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(Dishes.this,"Added to your cart",Toast.LENGTH_LONG).show();

    }

    }
