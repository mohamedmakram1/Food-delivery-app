package com.example.WaGbA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RestaurantsAdapter extends FirebaseRecyclerAdapter<RestaurantsClass, RestaurantsAdapter.myviewholder>
{
    private final DishInterfaceClass dishInterfaceClass;

    public RestaurantsAdapter(@NonNull FirebaseRecyclerOptions<RestaurantsClass> options, DishInterfaceClass dishInterfaceClass) {
        super(options);
        this.dishInterfaceClass = dishInterfaceClass;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull RestaurantsClass RestaurantsClass)
    {
       holder.name.setText(RestaurantsClass.getName());
       Glide.with(holder.img.getContext()).load(RestaurantsClass.getPurl()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_iteem,parent,false);
       return new myviewholder(view,dishInterfaceClass);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name;
        public myviewholder(@NonNull View itemView, DishInterfaceClass dishInterfaceClass)
        {
            super(itemView);
            img=itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( RestaurantsAdapter.this.dishInterfaceClass !=null){
                        int pos= getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            RestaurantsAdapter.this.dishInterfaceClass.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
