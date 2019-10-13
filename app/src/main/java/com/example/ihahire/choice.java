package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choice extends AppCompatActivity {

    private Button mBuy;
    private Button mSell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        mBuy=(Button)findViewById(R.id.buy);


     mBuy.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(choice.this, buy.class);
               startActivity(intent);

           }
       });

        mSell=(Button)findViewById(R.id.sell);
        mSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(choice.this,sell.class);
                startActivity(intent1);
            }
        });




    }
}
