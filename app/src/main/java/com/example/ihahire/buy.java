package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class buy extends AppCompatActivity {

    private ListView mProductList;
    private TextView mTitle;

    private String[] products = new String[]{"Cabbage", "carrot","","brocoli", "persil", "eegplant", "coniflower", "black bean", "peas", "lentil", "potatoes", "pumpinks",
            "sweet potatoes", "garlic", "basil", "coriander", "parsely", "lettuce", "peppers", "tomatoes"};
//    private String[] size= new String[]{"medium","Big","small","tall","red","green","leaf"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        mProductList=(ListView)findViewById(R.id.productList);
        mTitle=(TextView)findViewById(R.id.title);

        ArrayAdapter tobuy =new ArrayAdapter(this,android.R.layout.simple_list_item_1,products);
        mProductList.setAdapter(tobuy);

        mProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String products=((TextView)view).getText().toString();
                Toast.makeText(buy.this, products, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        mTitle.setText("This products are available ,now ");

    }
}

