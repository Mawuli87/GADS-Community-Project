package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.messieyawo.salesapp.ProductDetailActivity.MY_PREFS_NAME1;
import static com.messieyawo.salesapp.UserLogin.MY_PREFS_NAME;


public class SingleProductActivity extends AppCompatActivity {
    TextView pName,pDescription,pPrice,pCategory;
    ImageView pImage,pImage1,AddTocart,RemoveFromCart;
    int pId;
    String Name,Description,Price,Image,Category;


    private   TextView mProductName, mProductDate, mProductPrice, mProductDescription;
    public static int cart_count = 0;
    private ImageView mProductImage,mAdd,mRemove;
    private Button addToCart,addToFavorite,buy,BuyThis;
    String  User_Id, TotalQuantity;
    int res;

    ProgressDialog progressDialog;

    SharedPreferences prefs;
    String token,name,address;
    String name1;


    TextView textCartItemCount,tvQty,mProduct_total,mProduct_total_1;
    int mCartItemCount = 0;
    //product quantity
    String productName,productDescr,productPrice, productImg;
    int Id;
    int month;
    final int[] number = {0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);




        // pId= findViewById(R.id.single_itemId);
        pName = findViewById(R.id.single_item_name);
        pCategory = findViewById(R.id.single_item_category);
        pPrice = findViewById(R.id.single_item_price);
        mProductPrice = findViewById(R.id.product_price);
        pDescription = findViewById(R.id.single_item_description);
        pImage = findViewById(R.id.imageBig);
        pImage1 = findViewById(R.id.single_item_image);
        BuyThis = findViewById(R.id.buyThis);

        SharedPreferences sp = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        name1 = sp.getString("number","");


        //
        addToCart = findViewById(R.id.add_to_cart);
        //addToFavorite = findViewById(R.id.add_to_favorite);
        // buy = findViewById(R.id.buy);
        mAdd = findViewById(R.id.increase);
        mRemove = findViewById(R.id.remove);
        tvQty = findViewById(R.id.product_qty);



         Intent intent = getIntent();
        Id =  intent.getIntExtra("id",0);
        Name = intent.getStringExtra("name");
        Price = intent.getStringExtra("price");
        Category = intent.getStringExtra("category");
        Description = intent.getStringExtra("description");
        Image= intent.getStringExtra("photo");


        pName.setText(Name);
        pCategory.setText(Category);
        pDescription.setText(Description);
        pPrice.setText(Price);
        //mProductPrice.setText(""+Id);
        mProductPrice.setText(Price);

        Picasso.get().load(Image).into(pImage);
        Picasso.get().load(Image).into(pImage1);

         month = Integer.parseInt(Price);
    //

        //calculate items
        tvQty.setText("" + number[0]);
        //handle buy method




        //handle increase button
        mAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                number[0] = number[0] + 1;
                tvQty.setText("" + number[0]);



            }
        });
        //handle remove button
        mRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (number[0] == 0) {
                    tvQty.setText("" + number[0]);
                }

                if (number[0] > 1) {




                        number[0] = number[0] - 1;
                        tvQty.setText("" + number[0]);



                }
            }
        });


        //handle add and favorte
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 OrderActivity();
            }
        });

        BuyThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetIt();
            }
        });

    }

    private void GetIt() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
       alert.setTitle(name1 +" "+"item");
       alert.setMessage(

                    "Total amount  : "
                       + "\n"
                       + " Price: " + Price
                       + "\n"
                       + "Name: " + Name
                       + "\n"
                       +"Category: "+ Category

                       +"\n"
                       + "---------------------------------------------- "
                       + "\n"
                       + "Total Amount = " + "GH₵ " +  number[0]*month

                       );



        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        alert.setPositiveButton("okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }


    private void OrderActivity() {
        String URL_DATA = "http://onlineus.info/SDG2020/SalesWeb/temp_order.php?mobile="+name1+"&itemId="+Id+"&qty="+ number[0]+"&image="+Image;

        final ProgressDialog progressDialog = new ProgressDialog(SingleProductActivity.this);
        progressDialog.setMessage("Loading "+name1+" cart data....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Intent io = new Intent(SingleProductActivity.this,OrderActivity.class);
                        io.putExtra("number",name1);

                        startActivity(io);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(SingleProductActivity.this);
        requestQueue.add(stringRequest);




    }
}