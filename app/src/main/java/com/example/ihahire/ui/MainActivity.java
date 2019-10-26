package com.example.ihahire.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ihahire.R;

public class MainActivity extends AppCompatActivity {
    private  Button mVasy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVasy=(Button)findViewById(R.id.vasy);
        mVasy.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                //Toast to show the user that  buying  choiceActivity is loading
                Toast.makeText(MainActivity.this, "Welcome!!!", Toast.LENGTH_LONG).show();

                Intent intent= new Intent(MainActivity.this, choiceActivity.class);
                startActivity(intent);
            }


        });

    }


}