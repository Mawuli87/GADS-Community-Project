package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity {
  public static final String MY_PREFS_NAME = "userdata";
    EditText login_number,login_password;

    Button login_btn,goSignUpBtn;

    String loginNtext,loginPtext,loginNtextP;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        login_number = findViewById(R.id.login_mobile);

        login_password = findViewById(R.id.login_password);


        login_btn = findViewById(R.id.login_btn);
        goSignUpBtn = findViewById(R.id.go_to_signup);

        sp = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);


        loginNtext = login_number.getText().toString().trim();
        loginPtext = login_password.getText().toString().trim();




        goSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserLogin.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginNtextP = login_number.getText().toString().trim();


                SharedPreferences.Editor editor = sp.edit();
                editor.putString("number",loginNtextP);
                editor.apply();



                final ProgressDialog progressDialog = new ProgressDialog(UserLogin.this);
                progressDialog.setMessage("Checking your credentials");
                progressDialog.show();


                final String URL_ADD_POST ="http://onlineus.info/SDG2020/SalesWeb/login.php?mobile=" +loginNtext+"&password=" + loginPtext;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD_POST,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                if (response.equals("0")) {

                                    progressDialog.dismiss();

                                    Toast.makeText(UserLogin.this,
                                            "No login information found,please register",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserLogin.this, "login successful", Toast.LENGTH_LONG).show();

                                   Intent i = new Intent(UserLogin.this,HomeActivity.class);
                                   i.putExtra("n",loginNtext);
                                   startActivity(i);



                                }



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(UserLogin.this,"Adding item to cart failed"+error.toString(),Toast.LENGTH_LONG).show();

                            }
                        });


                RequestQueue requestQueue = Volley.newRequestQueue(UserLogin.this);
                requestQueue.add(stringRequest);


            }
        });
    }
}