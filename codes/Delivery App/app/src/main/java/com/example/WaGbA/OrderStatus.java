package com.example.WaGbA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderStatus extends AppCompatActivity {
    private TextView accept;
    private FirebaseDatabase firebaseD;
    private DatabaseReference readData;
    private EditText UsrMail;
    private Button lsbtn,Restaurant;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        UsrMail=findViewById(R.id.usrml);
        firebaseD=FirebaseDatabase.getInstance();
        accept=findViewById(R.id.confTv);
         lsbtn=findViewById(R.id.lst);
         Restaurant=findViewById(R.id.button);
         Restaurant.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(OrderStatus.this,RestaurantsPage.class);
                 startActivity(intent);
             }
         });
         firebaseD=FirebaseDatabase.getInstance();
         lsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!UsrMail.getText().toString().isEmpty()) {

                    readData=firebaseD.getReference().child("Accepted Orders")
                            .child(UsrMail.getText().toString());

                    readData.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String val = snapshot.getValue().toString();
                            accept.setText(val);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else { Intent getInt=getIntent();
                    accept.setText( getInt.getStringExtra("firstState"));

            }
            }
        });

    }

}