package com.example.ihahire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Sell extends AppCompatActivity {
    public  static  final String TAG=MainActivity.class.getSimpleName();

    private  EditText mName;
    private  EditText mQuantity;
    private  EditText mPrice;
    private  Button  mVasy;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName=(EditText)findViewById(R.id.name);
//        mQuantity=(EditText)findViewById(R.id.quantity);
//        mPrice=(EditText)findViewById(R.id.price);
//        mVasy=(Button)findViewById(R.id.vasy);

        mVasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                String name= mName.getText().toString();
                Intent intent= new Intent(Sell.this,Buy.class);
                intent.putExtra("name",name);

//                String price=mPrice.getText().toString();
//                Intent intent1= new Intent(Sell.this,Buy.class);
//                intent1.putExtra("price",price);
//
//                String quantity= mQuantity.getText().toString();
//                Intent intent2= new Intent(Sell.this,Buy.class);
//                intent2.putExtra("qty",quantity);

            }
        });
    }



}
