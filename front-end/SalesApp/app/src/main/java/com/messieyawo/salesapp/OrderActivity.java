package com.messieyawo.salesapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.messieyawo.salesapp.order.orderAdaptor;
import com.messieyawo.salesapp.order.orderItem;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.messieyawo.salesapp.UserLogin.MY_PREFS_NAME;

public class OrderActivity extends AppCompatActivity {

    String number,usernumber;
    ImageView orderImg;
    String RproductName,RproductDescription,RproductPrice, RproductCategory, RproductImage;
    int  RproductID,RpQty,allTotal;
    String TotalQuantity;
    ImageView totalOrder;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<orderItem> orderItems;
    String total;
    int p, allp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SharedPreferences sp = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        number = sp.getString("number","");

         Intent intent = getIntent();
         usernumber = intent.getStringExtra("number");



        recyclerView = findViewById(R.id.order_lv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        orderItems =  new ArrayList<>();

        final String URL_DATA ="http://onlineus.info/SDG2020/SalesWeb/get_cart_data.php?mobile="+usernumber;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading "+usernumber+"orders please wait....");
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



                                orderItem item = new orderItem(

                                        RproductID  =  o.getInt("id"),
                                        RproductName  = o.getString("name"),
                                        RproductPrice  =  o.getString("price"),
                                        RpQty =  o.getInt("qty"),
                                        RproductDescription  =   o.getString("mobile")



                                );

//                                 p = Integer.parseInt(RproductPrice);
//                                 allp = p * RpQty;
//                                 TextView tp = findViewById(R.id.order_total);
//                                 tp.setText(""+allp);


                                        orderItems.add(item);


                            }

                            adapter = new orderAdaptor(orderItems,getApplicationContext());

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


//        totalOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProceedToPay();
//            }
//        });

    }

    private void ProceedToPay() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Payment");
        alert.setMessage(

                "Total amount  : "
                        + "\n"
                        + " Price: " + RproductPrice
                        + "\n"
                        + "Name: " + RproductName
                        + "\n"
                        +"Category: "+ RpQty

                        +"\n"
                        + "---------------------------------------------- "
                        + "\n"
                        + "Total Amount = " + "GH₵ " + allp

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.item_cansel:


               canselMyOrder();


                return true;


//           case R.id.item_confirm:
//
//                // Ex: launching new activity/screen or show alert message
//               confirmRequest();
//
//
//                return true;



            case R.id.item_back:

                // Ex: launching new activity/screen or show alert message
                Intent menuintent = new Intent(OrderActivity.this,HomeActivity.class);
                startActivity(menuintent);

                return true;




            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void confirmRequest() {

      //  String Confirm_URL = "http://192.168.43.235/projects/SalesWeb/confirm_order.php?mobile="+usernumber;


//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Putting together all orders of "+usernumber+" please wait....");
//        progressDialog.show();

//
//        StringRequest ConfirmRequest = new StringRequest(Request.Method.POST,
//                Confirm_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.show();

                        OpenDialogBox();



//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(ConfirmRequest);





    }

    private void OpenDialogBox() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hello"+" "+usernumber);
        alert.setMessage(

                "Couldn't complete this part  : "
                        + "\n"
                        + " Why: " +"Run out of time"
                        + "\n"
                        + "Because i spent some time on BuildSDG Cohort 2020 "
                        + "\n"
                        +"Planned to finished: "+ "Yes, I will"

                        +"\n"
                        + "---------------------------------------------- "
                        + "\n"
                        + "Conclusion " + allp + "GADS 2020"

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


    private void canselMyOrder() {

        final String URL_DELETE = "http://onlineus.info/SDG2020/SalesWeb/cansel_order.php?mobile="+usernumber;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting all orders of "+usernumber+" please wait....");
        progressDialog.show();


        StringRequest OrderRequest = new StringRequest(Request.Method.POST,
                URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        if (response.equals("1")) {

                            progressDialog.dismiss();
                            Toast.makeText(OrderActivity.this, "All orders canceled successful", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(OrderActivity.this,HomeActivity.class);

                            startActivity(i);


                        }
                        else {
                            progressDialog.dismiss();



                            Toast.makeText(OrderActivity.this,
                                    "No order found in the cart,please order",
                                    Toast.LENGTH_LONG
                            ).show();

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
        requestQueue.add( OrderRequest);



    }
}