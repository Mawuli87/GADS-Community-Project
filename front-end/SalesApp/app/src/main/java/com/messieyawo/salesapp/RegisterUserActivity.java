package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


public class RegisterUserActivity extends AppCompatActivity {

EditText editTextName,editTextEmail,editTextMobile,editTextPassword,editConfirmPassword;
Button cirRegisterButton;

private ProgressBar loading;
private static String reg_url = "http://onlineus.info/SDG2020/SalesWeb/add_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        changeStatusBarColor();


        cirRegisterButton = findViewById(R.id.cirRegisterButton);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        editConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        loading = findViewById(R.id.loading);


        cirRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editTextName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "Name field must not be empty", Toast.LENGTH_SHORT).show();
                } else if (editTextEmail.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "Email field must not empty ", Toast.LENGTH_SHORT).show();
                } else if (editTextMobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "Mobile field must not be empty", Toast.LENGTH_SHORT).show();
                } else if (editTextPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "Password field must not be empty", Toast.LENGTH_SHORT).show();
                }else if (editConfirmPassword.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(RegisterUserActivity.this, "Password field must not be empty", Toast.LENGTH_SHORT).show();

                }
                 else {

                    loading.setVisibility(View.VISIBLE);
                    cirRegisterButton.setVisibility(View.GONE);

                    final String name = editTextName.getText().toString().trim();
                    final String email = editTextEmail.getText().toString().trim();
                    final String mobile = editTextMobile.getText().toString().trim();
                    final String password = editTextPassword.getText().toString().trim();
                    final String confirmPassword = editConfirmPassword.getText().toString().trim();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                loading.setVisibility(View.GONE);
                                cirRegisterButton.setVisibility(View.VISIBLE);

                                if (success.equals("1")) {
                                    Toast.makeText(RegisterUserActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                    Intent lointent = new Intent(RegisterUserActivity.this,UserLogin.class);
                                    startActivity(lointent);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(RegisterUserActivity.this, "Registration failed " + e.toString(), Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.GONE);
                                cirRegisterButton.setVisibility(View.VISIBLE);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(RegisterUserActivity.this, "Registration failed " + error.toString(), Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            cirRegisterButton.setVisibility(View.VISIBLE);

                        }
                    }) {
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<>();
                            params.put("mobile", mobile);
                            params.put("email", email);
                            params.put("password", password);
                            params.put("name", name);
                            return params;

                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(RegisterUserActivity.this);
                    requestQueue.add(stringRequest);


                }
            }
        });
    }





    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,UserLogin.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}