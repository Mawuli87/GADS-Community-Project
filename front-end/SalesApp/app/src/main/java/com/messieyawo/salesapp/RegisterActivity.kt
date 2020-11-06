package com.messieyawo.salesapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        login_page.setOnClickListener {
            var i = Intent(this, UserLogin::class.java)
            startActivity(i)
        }

        reg_signup.setOnClickListener {



                //val showString = editText.text.toString()

                if (reg_password.text.toString().equals(confirm_password.text.toString()) && reg_mobile.text.toString() != "") {


                    val progressDialog = ProgressDialog(this)
                    progressDialog.setTitle("User Registration")
                    progressDialog.setMessage("Registration in progress, please wait")
                    progressDialog.show()


                    var url =
                        "http://onlineus.info/SDG2020/SalesWeb/add_user.php?mobile=" + reg_mobile.text.toString() +
                                "&password=" + reg_password.text.toString() + "&name=" + reg_name.text.toString() + "&address=" +
                                reg_address.text.toString()
                    var rq: RequestQueue = Volley.newRequestQueue(this)
                    var sr = StringRequest(Request.Method.POST, url, { response ->
                        if (response.equals("0")) {

                            progressDialog.dismiss()

                            Toast.makeText(
                                this,
                                "This mobile number has already been taken",
                                Toast.LENGTH_LONG
                            ).show();
                        } else {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG)
                                .show();
                            UserInfo.mobile = reg_mobile.text.toString();
                            var i = Intent(this, UserLogin::class.java)

                            startActivity(i)
                        }

                    }, { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show();
                    })

                    rq.add(sr)
                } else {

                    Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
                }



        }

        fun onLoginClick(view: View) {
            var i = Intent(this, UserLogin::class.java)
            startActivity(i)
        }
    }

}