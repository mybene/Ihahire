package com.example.ihahire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Buy extends AppCompatActivity {

    private TextView mBuyList;
    private ListView mProductList;
    private String[] products = new String[]{"Cabbage", "carrot","","brocoli", "persil", "eegplant", "coniflower", "black bean", "peas", "lentil", "potatoes", "pumpinks",
            "sweet potatoes", "garlic", "basil", "coriander", "parsely", "lettuce", "peppers", "tomatoes"};

    protected void onCreate(Bundle savedInstancestate, Object adapterView, String name) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_buy);
        mProductList = (ListView) findViewById(R.id.productList);
        mBuyList = (TextView) findViewById(R.id.buyList);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,products);
        mProductList.setAdapter(adapter);

        Intent intent=getIntent();

        String buylist= intent.getStringExtra("name");
        mBuyList.setText("Name of product:"+name);
    }
}

//        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,products);
//        mProductList.setAdapter(adapter);
//
//        mProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                @Override
//                        public void onItemClick(AdapterView<?>adapterView,View view, int i, long l){}
//            }
//        });
//        Intent intent = getIntent();
////        Intent intent1=getIntent();
////        Intent intent2=getIntent();
//        String name = intent.getStringExtra("name");
////        String price= intent.getStringExtra("price");
////        String quantity=intent.getStringExtra("quantity");
//        mBuyList.setText("Product Name:" + name);
//    }
//}
//
