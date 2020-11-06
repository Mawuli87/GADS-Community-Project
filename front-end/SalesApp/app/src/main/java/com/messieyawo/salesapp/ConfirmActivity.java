package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmActivity extends AppCompatActivity {
    String usernumber, TotalQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Intent st = getIntent();
        usernumber = st.getStringExtra("number");


        String AddAll_URL = "http://onlineus.info/SDG2020/SalesWeb/get_total.php?bill_no="+usernumber;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Putting together all orders of "+usernumber+" please wait....");
        progressDialog.show();


        StringRequest AddAllRequest = new StringRequest(Request.Method.GET,
                AddAll_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            TotalQuantity   =  jsonObject.getString("message");

                            if (success.equals("1")){

                                getItemDetails();

                            }
                        }catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(ConfirmActivity.this,"Adding item to cart failed"+e.toString(),Toast.LENGTH_LONG).show();

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
        requestQueue.add(AddAllRequest);



    }

    private void getItemDetails() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_two, null);
        final TextView etPrice = alertLayout.findViewById(R.id.totalAmount);
        final Button etPay = alertLayout.findViewById(R.id.payTotal);
        etPrice.setText( TotalQuantity );
        //      final EditText etNumber = alertLayout.findViewById(R.id.et_number);
        etPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ConfirmActivity.this,HomeActivity.class);
                startActivity(newIntent);
            }
        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        //       etNumber.setText(Userphone);
        // disallow cancel of AlertDialog on click of back button and outside touch
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
}