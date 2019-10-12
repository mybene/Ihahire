package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private  Button mBuying;
    private Button  mSelling;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBuying=(Button).findById(R.id.buying);
        mBuying.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View b){
                Intent intent= new Intent(MainActivity.this,Buy.class);
                startActivity(intent);
            }
        });
        mSelling=(Button).findById(R.id.selling);
        mSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                Intent intent= new Intent(MainActivity.this,sell.class);
                startActivity(intent);
            }
        });
    }
}
