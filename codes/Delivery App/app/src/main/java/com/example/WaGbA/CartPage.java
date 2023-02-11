package com.example.WaGbA;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CartPage extends AppCompatActivity {

    private TextView totPrice, totAmount, Delivery,Display,availability;
    private EditText userID;
    private ImageView img;
    private Intent receivePrice,receiveImg;
    private Button confirm,remove,add,sub,cash,credit;
    private int p,totalQuantity=1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private HashMap<String ,Object> orders;
    private String pMethod;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Orders").child("Order Details");


        img=findViewById(R.id.img1);
        totPrice=findViewById(R.id.tPrice);
        totAmount=findViewById(R.id.total);
        Delivery=findViewById(R.id.delivery);
        add=findViewById(R.id.addbtn);
        sub=findViewById(R.id.subbtn);
        userID=findViewById(R.id.userId);
        availability=findViewById(R.id.availTv);
        cash=findViewById(R.id.cash);
        credit=findViewById(R.id.credit);

        Display=findViewById(R.id.displayedt);
        receivePrice=getIntent();
        receiveImg=getIntent();
        String url=receiveImg.getStringExtra("orderImg");
        Glide.with(this).load(url).into(img);
        String price = receivePrice.getStringExtra("price");

        totPrice.setText(price);
        p= Integer.parseInt(totPrice.getText().toString());
        totAmount.setText(String.valueOf(p*totalQuantity));

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pMethod="Cash";

            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pMethod="Credit";
            }
        });

      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(totalQuantity<100) {
                  totalQuantity++;
                  Display.setText(String.valueOf(totalQuantity));
                  totAmount.setText(String.valueOf(p*totalQuantity));
                  if(totalQuantity>=10){
                      availability.setText("unavailable");
                  }

              }
          }
      });

      sub.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (totalQuantity > 1) {
                  totalQuantity--;
                  Display.setText(String.valueOf(totalQuantity));
                  totAmount.setText(String.valueOf(p * totalQuantity));
                  if(totalQuantity<=10){
                      availability.setText("available");
                  }
              }
          }
      });



        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(availability.getText().toString()=="unavailable"){
                    availability.setError("we have only 10 dishes of this meal!");
                    availability.requestFocus();
                    return;

                }else {

                    Intent OrderStatIntent = new Intent(CartPage.this, OrderStatus.class);
                    orders = new HashMap<>();
                    orders.put("meal details",url);
                    orders.put("Quentity", totalQuantity);
                    orders.put("payment method", pMethod);
                    String msg = "Waiting for acceptance";
                    orders.put("Order status", msg);
                    if (userID.getText().toString().isEmpty()) {
                        userID.setError("your id is required to track your order!");
                        userID.requestFocus();
                        return;

                    } else if (userID.length() < 7) {
                        userID.setError("your id must be<=7!");
                        userID.requestFocus();
                        return;

                    } else {
                        orders.put("UserID", userID.getText().toString());
                    }
                    databaseReference.setValue(orders);
                    OrderStatIntent.putExtra("firstState",msg);
                    startActivity(OrderStatIntent);
                }
            }
        });

        remove=findViewById(R.id.Remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.setValue(null);
                Intent intent=new Intent(CartPage.this,Dishes.class);
                startActivity(intent);

            }
        });



    }
}