package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class newItems extends AppCompatActivity {
//    private TextView mArticles;
    private TextView mItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_items);

//        mArticles = (TextView) findViewById(R.id.articles);
        mItemView=(TextView)findViewById(R.id.itemView);

        Intent intent = getIntent();

        String name=intent.getStringExtra("name");

        mItemView.setText("The received product is " + name);
    }
}

