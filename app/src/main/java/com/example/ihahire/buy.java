package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;



public class buy extends AppCompatActivity {


    private GridView mProductList;

    private String[] products = new String[]{"Cabbage", "carrot","","brocoli", "persil", "eegplant", "coniflower", "black bean", "peas", "lentil", "potatoes", "pumpinks",
            "sweet potatoes", "garlic", "basil", "coriander", "parsely", "lettuce", "peppers", "tomatoes"};
//    private String[] size= new String[]{"medium","Big","small","tall","red","green","leaf"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        mProductList=(GridView)findViewById(R.id.productList);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,products);
        mProductList.setAdapter(adapter);
//
//        Intent intent=getIntent();
//
//        String buylist= intent.getStringExtra("name");
//        mBuyList.setText("Name of product:"+name );
    }
}

