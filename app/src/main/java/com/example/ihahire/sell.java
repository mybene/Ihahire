package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class sell extends AppCompatActivity {
    private EditText mName;
    private Button mSelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

      mName=(EditText)findViewById(R.id.name);
      mSelling=(Button)findViewById(R.id.selling);

        mSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString();
//                log.d(TAG,name);
                Intent intent=new Intent(sell.this,newItems.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

    }
}
