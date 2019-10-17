package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class choice extends AppCompatActivity {
    @BindView(R.id.buy) Button mBuy;
    @BindView(R.id.sell) Button mSell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

      ButterKnife.bind(this);

        mBuy.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(choice.this, buy.class);
               startActivity(intent);

           }
       });

        mSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(choice.this,sell.class);
                startActivity(intent1);
            }
        });




    }
}
