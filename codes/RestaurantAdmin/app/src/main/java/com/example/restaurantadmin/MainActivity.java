package com.example.restaurantadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button Accept,OnWay,Shipped,Deliverd;
    private TextView Received;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseRef, writeRef;
    String UserID;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase=FirebaseDatabase.getInstance();

        Accept=findViewById(R.id.accept);
        OnWay=findViewById(R.id.onWay);
        Shipped=findViewById(R.id.ship);
        Deliverd=findViewById(R.id.deleiverd);
        Received=findViewById(R.id.receiveOrder);



        databaseRef=firebaseDatabase.getReference().child("Orders").child("Order Details");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String val =snapshot.child("Order status").getValue().toString();
                Received.setText(val);
                UserID =snapshot.child("UserID").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRef.child("Order status").setValue("Accepted");
                writeRef=firebaseDatabase.getReference().child("Accepted Orders").child(UserID);
                writeRef.setValue("Accepted");

            }
        });

        Shipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeRef.setValue("Order Cooking finshed and ready for shipping");
                Received.setText("ready for shipping");
            }
        });

        OnWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeRef.setValue("On Way");
                Received.setText("On way");
            }
        });

        Deliverd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeRef.setValue("Order Delivered");
                Received.setText("Delivered");

            }
        });

    }


}