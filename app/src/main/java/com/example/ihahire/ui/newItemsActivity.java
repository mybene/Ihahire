package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;

public class newItemsActivity extends AppCompatActivity {
    //    private TextView mArticles;
    private TextView detailProduct;

    private SharedPreferences ProductsharedPreferences;
    private String recentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_items);

//        mArticles = (TextView) findViewById(R.id.articles);
        detailProduct = (TextView) findViewById(R.id.detail);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String shop = intent.getStringExtra("Shop");
        String phone = intent.getStringExtra("phone");

        ProductsharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        recentProduct = ProductsharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if(recentProduct!=null){
        getProducts(recentProduct);
        }

        detailProduct.setText("The  selling product's name : " + name + " is available at " + shop + " with contact " + phone);
    }

    private void getProducts(String recentProduct) {
    }
}

