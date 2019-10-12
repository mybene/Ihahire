package com.example.ihahire;

import android.os.Bundle;
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
        mQuantity=(EditText)findViewById(R.id.quantity);
        mPrice=(EditText)findViewById(R.id.price);
    }



}
