package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  Button mBuying;
    private Button  mSelling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBuying=(Button)findViewById(R.id.buying);
        mBuying.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View b){
                //Toast to show the user that  buying  choice is loading
                    Toast.makeText(MainActivity.this,"Buy!",Toast.LENGTH_LONG).show();

                Intent intent= new Intent(MainActivity.this,Buy.class);
                startActivity(intent);
            }
        });

        mSelling=(Button)findViewById(R.id.selling);
        mSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                //Toast to show the user that  selling  choice is loading
                Toast.makeText(MainActivity.this,"sell",Toast.LENGTH_LONG).show();
                Intent intent= new Intent(MainActivity.this,Sell.class);
                startActivity(intent);
            }
        });
    }


}