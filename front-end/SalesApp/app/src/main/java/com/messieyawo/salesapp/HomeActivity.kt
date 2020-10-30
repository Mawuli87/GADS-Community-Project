package com.messieyawo.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast

import com.android.volley.Request
import com.android.volley.RequestQueue

import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var url="http://onlineus.info/SDG2020/SalesWeb/get_category.php";
        var list = ArrayList<String>();
        var rq:RequestQueue= Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null, { response ->
        for (x in 0..response.length()-1)
            list.add(response.getJSONObject(x).getString("category"))
            var adp = ArrayAdapter(this,R.layout.model,list)


            home_category.adapter = adp;

        }, { error ->
        Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()


        })
        rq.add(jar)
      home_category.setOnItemClickListener { adapterView, view, i, l ->

         var cat:String= list[i]
          var obj= Intent(this,ProductDetailActivity::class.java)
          obj.putExtra("cat",cat)

          startActivity(obj)
      }










    }
}