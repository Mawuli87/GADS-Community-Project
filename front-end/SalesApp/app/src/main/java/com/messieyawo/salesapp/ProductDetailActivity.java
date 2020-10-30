package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ProductDetailActivity extends AppCompatActivity {
   public static final String MY_PREFS_NAME1 = "img" ;
    //    public static final String MY_PREFS_NAME = "number";
    //product quantity
    String RproductName,RproductDescription,RproductPrice, RproductCategory, RproductImage;
    int  RproductID;



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<categoryItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        String  cat = intent.getStringExtra("cat");








        recyclerView = findViewById(R.id.item_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

          cartItems =  new ArrayList<>();


            final String URL_DATA = "http://onlineus.info/SDG2020/SalesWeb/get_items.php?category="+cat;

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading "+cat+" data....");
            progressDialog.show();


            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL_DATA,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                         progressDialog.dismiss();

                            try {

                                JSONArray jsonArray = new JSONArray(response);

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject o = jsonArray.getJSONObject(i);


                                        categoryItem item = new  categoryItem(

                                                RproductID  =  o.getInt("id"),
                                                RproductName  = o.getString("name"),
                                                RproductPrice  =  o.getString("price"),
                                                RproductDescription  =   o.getString("description"),
                                                RproductCategory = o.getString("category"),
                                                RproductImage   =  o.getString("photo")



                                        );


                                        cartItems.add(item);

                                    }

                                adapter = new ItemAdapter(cartItems,getApplicationContext());

                                recyclerView.setAdapter(adapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


    }




}