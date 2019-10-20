package com.example.ihahire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class buy extends AppCompatActivity {

    private ListView mProductList;
    private TextView mTitle;

    private String[] products = new String[]{"Cabbage", "carrot", "Lengalenga", "brocoli", "persil", "eegplant", "coniflower", "black bean", "peas", "lentil", "potatoes", "pumpinks",
            "sweet potatoes", "garlic", "basil", "coriander", "parsely", "lettuce", "peppers", "tomatoes"};

    private String[] location = new String[]{"Frulep", "Simba Supermarket", "La Galette", "KIME Supermarket", "German Butchery"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        mProductList = (ListView) findViewById(R.id.productList);
        mTitle = (TextView) findViewById(R.id.title);

        ArrayAdapter tobuy = new ArrayAdapter(this, android.R.layout.simple_list_item_1, products);
        mProductList.setAdapter(tobuy);

        mProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String products = ((TextView) view).getText().toString();
                Toast.makeText(buy.this, products, Toast.LENGTH_LONG).show();
            }
        });


        YelpApi client = YelpClient.getClient();

        Call<Search> call = client.getProducts(location, "products");

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.isSuccessful()) {
                    List<Business> productsList = response.body().getBusinesses();
                    String[] articles = new String[productsList.size()];
                    String[] categories = new String[productsList.size()];

                    for (int i = 0; i < articles.length; i++) {
                        articles[i] = productsList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = productsList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    BuyArrayAdapter tobuy = new BuyArrayAdapter(buy.this, android.R.layout.activity_list_item, articles, categories);
                    mProductList.setAdapter(tobuy);

                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }

        });

    }
}

