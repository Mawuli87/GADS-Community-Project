package com.messieyawo.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.messieyawo.salesapp.UserLogin.MY_PREFS_NAME;

public class PaymentActivity extends AppCompatActivity {

    TextView pName,pQty,pprice,pTotal,cardUser;
    String getName,getqty,getPrice,sName,sTotal,sPrice,sQty,email;
    int tp,tpt,tp1;
    Button sendOrderP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String name = prefs.getString("mobile", " ");
        email="nyonouglokoffi8@gmail.com";

        pName = findViewById(R.id.porder_name);
        pTotal =findViewById(R.id.porder_total);
        pprice = findViewById(R.id.porder_price);
        pQty = findViewById(R.id.porder_qty);
        cardUser = findViewById(R.id.tv_member_name);
        sendOrderP = findViewById(R.id.sendOrder);



//        Intent pItent = getIntent();
//        getName = pItent.getStringExtra("name");
//        getPrice = pItent.getStringExtra("price");
//        getqty = pItent.getStringExtra("qty");



       // pTotal.setText(tpt);

        //get intent from singleProductActivity
        Intent sitent = getIntent();
        sName = sitent.getStringExtra("Sname");
        sPrice = sitent.getStringExtra("Sprice");
        sTotal = sitent.getStringExtra("Stotal");
        sQty = sitent.getStringExtra("Sqty");
        cardUser.setText(name);
//        int csTotal =  Integer.parseInt(sTotal);



            pName.setText(sName);
            pTotal.setText("Ghc "+sTotal);
            pprice.setText( sPrice);
            pQty.setText(sQty);


//        pName.setText(getName);
//        pprice.setText(getPrice);
//        pQty.setText(getqty);
//
//
//        tp = Integer.parseInt(getPrice);
//        tp1 = Integer.parseInt(getqty);
//        tpt = tp * tp1;
//        pTotal.setText("Ghc "+tpt);



        sendOrderP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                it.putExtra(Intent.EXTRA_SUBJECT,"Ordering of "+sName+". Total Amount Ghc:"+sTotal+". Thanks for choosing us.We will process your order ASAP.");
                it.putExtra(Intent.EXTRA_TEXT,tpt);
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Place order of "+sName+"  from Koffi Nyonouglo.We will get back to ASAP.Thanks for choosing us"));
            }
        });




    }
}