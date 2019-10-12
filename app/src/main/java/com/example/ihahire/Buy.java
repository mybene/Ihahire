package com.example.ihahire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;
import java.text.CollationElementIterator;

public class Buy extends AppCompatActivity {

    private TextView mBuyList;
    ;
    @Override
    protected  void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.buy_activity);
        Intent intent=getIntent();
//        Intent intent1=getIntent();
//        Intent intent2=getIntent();
        String name=intent.getStringExtra("name");
//        String price= intent.getStringExtra("price");
//        String quantity=intent.getStringExtra("quantity");
        mBuyList.setText("Product Name:"+name);
    }
}
