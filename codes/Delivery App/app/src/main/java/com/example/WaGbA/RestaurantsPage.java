package com.example.WaGbA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.WaGbA.databinding.ActivityRestaurantsPageBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantsPage extends AppCompatActivity implements DishInterfaceClass
{
    private ActivityRestaurantsPageBinding binding;
    private RestaurantsAdapter adapter;
    private  Intent orderIntent,CartIntent;
    private Button Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantsPageBinding .inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.recview.setLayoutManager(new LinearLayoutManager(this));
        Order=findViewById(R.id.orderPagebtn);
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RestaurantsPage.this,OrderStatus.class);
                startActivity(intent);
            }
        });



        FirebaseRecyclerOptions<RestaurantsClass> options =
                new FirebaseRecyclerOptions.Builder<RestaurantsClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurants"), RestaurantsClass.class)
                        .build();

        adapter=new RestaurantsAdapter(options, this);
        binding.recview.setAdapter(adapter);

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
        Intent intent=new Intent(RestaurantsPage.this,Dishes.class);
        intent.putExtra("pos",position);
        startActivity(intent);
      }
    }
