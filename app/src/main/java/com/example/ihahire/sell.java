package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;



public class sell extends AppCompatActivity implements View.OnClickListener {

  @BindView(R.id.name) EditText mName;
  @BindView(R.id.selling) Button mSelling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        ButterKnife.bind(this);

//        mSelling=(Button)findViewById(R.id.sell);
//        mName=(EditText)findViewById(R.id.name);
        mSelling.setOnClickListener(this);


    }


    @Override
    public   void onClick(View mSelling) {

            String name = mName.getText().toString();
            Toast.makeText(sell.this, "Your product is received!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(sell.this, newItems.class);
            intent.putExtra("name", name);
            startActivity(intent);


    }
}






