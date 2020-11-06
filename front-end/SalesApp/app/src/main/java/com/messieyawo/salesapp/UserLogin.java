package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UserLogin extends AppCompatActivity {
  public static final String MY_PREFS_NAME = "userdata";
    EditText login_number,login_password;

    Button login_btn;
    TextView goSignUpBtn;
    ImageView sendSignUp;


    String loginNtext,loginPtext,loginNtextP;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        login_number = findViewById(R.id.editTextName);

        login_password = findViewById(R.id.editTextPassword);
        sendSignUp = findViewById(R.id.sendSignUp);


        login_btn = findViewById(R.id.cirLoginButton);
        goSignUpBtn = findViewById(R.id.go_to_signup);





        goSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(UserLogin.this,RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

            }
        });

        sendSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UserLogin.this,RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });









       login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(UserLogin.this);
                progressDialog.setMessage("Logging you in please wait....");
                progressDialog.show();

                if (login_number.getText().toString().trim().isEmpty()) {
                    Toast.makeText(UserLogin.this, "Number field must not be empty", Toast.LENGTH_SHORT).show();
                } else if (login_password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(UserLogin.this, "Password field must not empty ", Toast.LENGTH_SHORT).show();

                }
                else {



                    final String mobile = login_number.getText().toString().trim();
                    final String password = login_password.getText().toString().trim();


                    String reg_url = "http://onlineus.info/SDG2020/SalesWeb/login.php?mobile="+mobile+"&password="+password;

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                               JSONObject jsonObject = new JSONObject(response);
                               String success = jsonObject.getString("success");
                               // JSONArray jsonArray = jsonObject.getJSONArray("login");


                                if (success.equals("1")) {
                                    progressDialog.dismiss();

//                                         for (int i=0; i<jsonArray.length(); i++){
//                                             String name = jsonObject.getString("name").trim();
//                                             String email = jsonObject.getString("email").trim();
//                                         }
                                    Toast.makeText(UserLogin.this, "login Successful", Toast.LENGTH_LONG).show();
                                    Intent lointent = new Intent(UserLogin.this,HomeActivity.class);
                                    startActivity(lointent);



                                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                    editor.putString("mobile", mobile);
                                    editor.apply();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(UserLogin.this, "login failed " + e.toString(), Toast.LENGTH_LONG).show();


                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(UserLogin.this, "login failed " + error.toString(), Toast.LENGTH_LONG).show();


                        }
                    }) {
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<>();
                            params.put("mobile", mobile);

                            params.put("password", password);

                            return params;

                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("Content-Type","application/x-www-form-urlencoded");
                            return params;
                        }
                    };




                    RequestQueue requestQueue = Volley.newRequestQueue(UserLogin.this);
                    requestQueue.add(stringRequest);


                }
            }
        });




//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (login_number.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(UserLogin.this, "Number field must not be empty", Toast.LENGTH_SHORT).show();
//                } else if (login_password.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(UserLogin.this, "Password field must not empty ", Toast.LENGTH_SHORT).show();
//
//                } else {
//
//                    loginNtextP = login_number.getText().toString().trim();
//
//
//                    final ProgressDialog progressDialog = new ProgressDialog(UserLogin.this);
//                    progressDialog.setMessage("Checking your credentials");
//                    progressDialog.show();
//
//
//                    final String URL_ADD_POST = "http://onlineus.info/SDG2020/SalesWeb/login.php?mobile=" + loginNtext + "&password=" + loginPtext;
//
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ADD_POST,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//
//
//                                    if (response.equals("1")) {
//
//
//                                        progressDialog.dismiss();
//                                        Toast.makeText(UserLogin.this, "login successful", Toast.LENGTH_LONG).show();
//
//                                        Intent i = new Intent(UserLogin.this, HomeActivity.class);
//                                        i.putExtra("n", loginNtext);
//                                        startActivity(i);
//
//
//                                    } else {
//
//                                        progressDialog.dismiss();
//
//                                        Toast.makeText(UserLogin.this,
//                                                "No login information found,please register",
//                                                Toast.LENGTH_LONG
//                                        ).show();
//
//                                    }
//
//
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    Toast.makeText(UserLogin.this, "Adding item to cart failed" + error.toString(), Toast.LENGTH_LONG).show();
//
//                                }
//                            });
//
//
//                    RequestQueue requestQueue = Volley.newRequestQueue(UserLogin.this);
//                    requestQueue.add(stringRequest);
//
//
//                }
//            }
//        });
//    }
//}



    }
}