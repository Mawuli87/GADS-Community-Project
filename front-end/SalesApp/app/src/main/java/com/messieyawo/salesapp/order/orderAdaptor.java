package com.messieyawo.salesapp.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.messieyawo.salesapp.R;
import com.messieyawo.salesapp.SingleProductActivity;
import com.messieyawo.salesapp.categoryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class orderAdaptor extends RecyclerView.Adapter<orderAdaptor.ViewHolder>{
    private List<orderItem> orderItems;
    private Context context;
    String user,etUsername,etEmail;


    ImageView  delete_image;
    public orderAdaptor(List<orderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;


    }


    @NonNull
    @Override
    public orderAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row,parent,false);

        final  ViewHolder viewHolder = new ViewHolder(v) ;
//        viewHolder.view_container.findViewById(R.id.rootLayout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productIntent = new Intent(context,SingleProductActivity.class);
//                productIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                productIntent.putExtra("id",orderItems.get(viewHolder.getAdapterPosition()).getId());
//                productIntent.putExtra("name",orderItems.get(viewHolder.getAdapterPosition()).getName());
//                productIntent.putExtra("price",orderItems.get(viewHolder.getAdapterPosition()).getPrice());
//                productIntent.putExtra("qty",orderItems.get(viewHolder.getAdapterPosition()).getQty());
//                productIntent.putExtra("mobile",orderItems.get(viewHolder.getAdapterPosition()).getMobile());
//
//                context.startActivity(productIntent);
//            }
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderAdaptor.ViewHolder holder, int position) {

        orderItem ctgItem = orderItems.get(position);
        // int ItemId = ctgItem.getId();
       // holder.idItem.setText(ItemId);
        holder.product_name.setText(ctgItem.getName());
        holder.price.setText(ctgItem.getPrice());
        holder.qty.setText(""+ctgItem.getQty());
        holder.mobile.setText(ctgItem.getMobile());







    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idItem,product_name,price,qty,mobile;
       ImageView view_container,removeFromCart,addToCart;
        // ImageView delete_image;

        public ViewHolder(final View itemView) {
            super(itemView);
           // idItem = itemView.findViewById(R.id.itemId);
            product_name = itemView.findViewById(R.id.order_name);
            qty = itemView.findViewById(R.id.order_qty);
            price = itemView.findViewById(R.id.order_price);
            delete_image = itemView.findViewById(R.id.deleteFromCart);
            mobile= itemView.findViewById(R.id.order_mobile);

          //  view_container = itemView.findViewById(R.id.rootLayout);

//            addToCart = itemView.findViewById(R.id.addToCart);



        }


    }

}
