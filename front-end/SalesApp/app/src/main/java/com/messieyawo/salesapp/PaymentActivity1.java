package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.messieyawo.salesapp.UserLogin.MY_PREFS_NAME;

public class PaymentActivity1 extends AppCompatActivity {
    TextView pName,pQty,pprice,pTotal, cardUser;
    String getName,getqty,getPrice,sName,sTotal,sPrice,sQty,email;
    int tp,tpt,tp1;
    Button sendOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String name = prefs.getString("mobile", " ");

        pName = findViewById(R.id.porder_name);
        pTotal =findViewById(R.id.porder_total);
        pprice = findViewById(R.id.porder_price);
        pQty = findViewById(R.id.porder_qty);
        cardUser = findViewById(R.id.tv_member_name);
        sendOrder = findViewById(R.id.sendOrder);
        email="nyonouglokoffi8@gmail.com";



        Intent pItent = getIntent();
        getName = pItent.getStringExtra("name");
        getPrice = pItent.getStringExtra("price");
        getqty = pItent.getStringExtra("qty");



//        // pTotal.setText(tpt);
//
//        //get intent from singleProductActivity
//        Intent sitent = getIntent();
//        sName = sitent.getStringExtra("Sname");
//        sPrice = sitent.getStringExtra("Sprice");
//        sTotal = sitent.getStringExtra("Stotal");
//        sQty = sitent.getStringExtra("Sqty");
////        int csTotal =  Integer.parseInt(sTotal);



//        pName.setText(sName);
//        pTotal.setText("Ghc "+sTotal);
//        pprice.setText( sPrice);
//        pQty.setText(sQty);


        pName.setText(getName);
        pprice.setText(getPrice);
        pQty.setText(getqty);
        cardUser.setText(name);


        tp = Integer.parseInt(getPrice);
        tp1 = Integer.parseInt(getqty);
        tpt = tp * tp1;
        pTotal.setText("Ghc "+tpt);



  sendOrder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent it = new Intent(Intent.ACTION_SEND);
          it.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
          it.putExtra(Intent.EXTRA_SUBJECT,"Ordering of "+getName+" Amount Ghc" +
                  "" +
                  ":"+tpt+". Thanks for choosing us.We will process your order ASAP.");
          it.putExtra(Intent.EXTRA_TEXT,tpt);
          it.setType("message/rfc822");
          startActivity(Intent.createChooser(it,"Place order of "+getName+"  from Koffi Nyonouglo.We will get back to ASAP.Thanks for choosing us"));
      }
  });




    }

}