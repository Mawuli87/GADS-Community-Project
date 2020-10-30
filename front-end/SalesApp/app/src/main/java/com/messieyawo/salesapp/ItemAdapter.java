package com.messieyawo.salesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private List<categoryItem> categoryItems;
    private Context context;
    String user,etUsername,etEmail;


    ImageView product_image, delete_image;
    public ItemAdapter(List<categoryItem> categoryItems, Context context) {
        this.categoryItems = categoryItems;
        this.context = context;


    }


    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row,parent,false);

        final  ViewHolder viewHolder = new ViewHolder(v) ;
        viewHolder.view_container.findViewById(R.id.rootLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productIntent = new Intent(context,SingleProductActivity.class);
                productIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                productIntent.putExtra("id",categoryItems.get(viewHolder.getAdapterPosition()).getId());
                productIntent.putExtra("name",categoryItems.get(viewHolder.getAdapterPosition()).getName());
                productIntent.putExtra("price",categoryItems.get(viewHolder.getAdapterPosition()).getPrice());
                productIntent.putExtra("category",categoryItems.get(viewHolder.getAdapterPosition()).getCategory());
                productIntent.putExtra("description",categoryItems.get(viewHolder.getAdapterPosition()).getDescription());
                productIntent.putExtra("photo",categoryItems.get(viewHolder.getAdapterPosition()).getPhoto());
                context.startActivity(productIntent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

        categoryItem ctgItem = categoryItems.get(position);
         int ItemId = ctgItem.getId();
       // holder.idItem.setText(ItemId);
        holder.product_name.setText(ctgItem.getName());
        holder.price.setText(ctgItem.getPrice());
        holder.category.setText(ctgItem.getCategory());
        holder.description.setText(ctgItem.getDescription());





        Picasso.get().load(ctgItem.getPhoto()).into(product_image);

    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idItem,product_name,price,category,description;
       ImageView view_container,removeFromCart,addToCart;
        // ImageView delete_image;

        public ViewHolder(final View itemView) {
            super(itemView);
           // idItem = itemView.findViewById(R.id.itemId);
            product_name = itemView.findViewById(R.id.item_name);
            category = itemView.findViewById(R.id.item_category);
            price = itemView.findViewById(R.id.item_price);
            product_image = itemView.findViewById(R.id.item_image);
            description = itemView.findViewById(R.id.item_description);

            view_container = itemView.findViewById(R.id.rootLayout);

//            addToCart = itemView.findViewById(R.id.addToCart);



        }


    }

}
