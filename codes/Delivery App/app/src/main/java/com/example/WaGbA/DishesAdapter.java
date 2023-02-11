 package com.example.WaGbA;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

 public class DishesAdapter extends FirebaseRecyclerAdapter<DishesClass,DishesAdapter.myviewholder> {

     private final DishInterfaceClass dishInterfaceClass;

     public DishesAdapter(@NonNull FirebaseRecyclerOptions<DishesClass> options, DishInterfaceClass dishInterfaceClass) {
         super(options);
         this.dishInterfaceClass = dishInterfaceClass;
     }

     @Override
     protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DishesClass DishesClass) {
         holder.pricetxt.setText(DishesClass.getPrice());
         Glide.with(holder.img.getContext()).load(DishesClass.getPurl()).into(holder.img);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(holder.img.getContext(),CartPage.class);
                 intent.putExtra("price",DishesClass.getPrice());
                 intent.putExtra("orderImg",DishesClass.getPurl());
                 holder.img.getContext().startActivity(intent);

             }
         });
     }

     @NonNull
     @Override
     public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dishes_item,parent,false);
         return new myviewholder(view,dishInterfaceClass);
     }

     class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView pricetxt;
        public myviewholder(@NonNull View itemView, DishInterfaceClass dishInterfaceClass)
        {
            super(itemView);
            img=itemView.findViewById(R.id.img1);
            pricetxt=(TextView)itemView.findViewById(R.id.priceTxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( DishesAdapter.this.dishInterfaceClass !=null){
                        int pos= getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            DishesAdapter.this.dishInterfaceClass.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }

}

