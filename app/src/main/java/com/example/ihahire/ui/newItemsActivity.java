package com.example.ihahire.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;

public class newItemsActivity extends AppCompatActivity {
//    private TextView mArticles;
    private TextView detailProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_items);

//        mArticles = (TextView) findViewById(R.id.articles);
        detailProduct=(TextView)findViewById(R.id.detail);

        Intent intent = getIntent();

        String name=intent.getStringExtra("name");

        detailProduct.setText("The received product is " + name);
    }
}

