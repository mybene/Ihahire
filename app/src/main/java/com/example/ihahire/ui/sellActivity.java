package com.example.ihahire.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ihahire.R;

import butterknife.BindView;
import butterknife.ButterKnife;



public class sellActivity extends AppCompatActivity implements View.OnClickListener {

  @BindView(R.id.name) EditText mName;
  @BindView(R.id.selling) Button mSelling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        ButterKnife.bind(this);

//        mSelling=(Button)findViewById(R.id.sellActivity);
//        mName=(EditText)findViewById(R.id.name);
        mSelling.setOnClickListener(this);


    }


    @Override
    public   void onClick(View mSelling) {

            String name = mName.getText().toString();
            Toast.makeText(sellActivity.this, "Your product is received!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(sellActivity.this, newItemsActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);


    }
}






