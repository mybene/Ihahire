package com.example.ihahire.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.R;
import com.example.ihahire.adapters.BuyListAdapter;
import com.example.ihahire.models.Business;
import com.example.ihahire.models.Search;
import com.example.ihahire.networks.YelpApi;
import com.example.ihahire.networks.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyListActivity extends AppCompatActivity  {


    public static final String TAG = BuyListActivity.class.getSimpleName();

//    private String[] products = new String[]{"straberry bavarois", "lemon cake", "banana cake", "chocolate moka cake", "cheese cake", "fruits tarte", "biche noel vanilla", "biche noel choco", "madelaine"};
//
//    private String[] shops = new String[]{"Frulep", "Simba Supermarket", "La Galette", "KIME Supermarket", "German Butchery", "Meru", "Frulep", "Simba Supermarket", "La Galette"};

//    @BindView(R.id.productTitle) ListView productList;
//    @BindView(R.id.shopTitle) TextView shopList;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private BuyListAdapter buyAdapter;

    public List<Business> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        ButterKnife.bind(this);


//        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String product = ((TextView) view).getText().toString();
//                Toast.makeText(BuyListActivity.this, product, Toast.LENGTH_LONG).show();
//            }
//        });


        Intent intent = getIntent();
        String product = intent.getStringExtra("product");
//        shopList.setText("The lis of shop have this cake " + product);


        YelpApi client = YelpClient.getClient();

        Call<Search> call = client.getProducts(product, "products");
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                hideProgressBar();

                if (response.isSuccessful()) {

                    products = response.body().getBusinesses();
                    buyAdapter = new BuyListAdapter(BuyListActivity.this,products);
                    mRecyclerView.setAdapter(buyAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((BuyListActivity.this));
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    getShops();
                } else {
                    showFailureMessage();
                }
            }


            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void getShops() {
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}



//        YelpApi client = YelpClient.getClient();
//
//        Call<Search> call = client.getProducts(location, "products");
//
//        call.enqueue(new Callback<Search>() {
//            @Override
//            public void onResponse(Call<Search> call, Response<Search> response) {
//                hideProgressBar();
//
//                if (response.isSuccessful()) {
//                    List<Business> productsList = response.body().getBusinesses();
//
//                    String[] products = new String[productsList.size()];
//                    String[] categories = new String[productsList.size()];
//
//                    for (int i = 0; i < products.length; i++) {
//                        products[i] = productsList.get(i).getName();
//                    }
//
//                    for (int i = 0; i < categories.length; i++) {
//                        Category category = productsList.get(i).getCategories().get(0);
//                        categories[i] = category.getTitle();
//                    }
//
//
//
//                    mBuy=response.body().getBusinesses();
//
//                    buyAdapter= new BuyListAdapter(BuyListActivity.this,mBuy);
//
//                    mRecyclerView.setAdapter(buyAdapter);
//                    RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(BuyListActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//
//

//
//                    BuyArrayAdapter adapter = new BuyArrayAdapter(BuyListActivity.this, android.R.layout.simple_list_item_1, products, categories);
//                    mItemListView.setAdapter(adapter);
//                    showmBuy();
//
//                } else {
//                    showUnsuccessfulMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Search> call, Throwable t) {
//                hideProgressBar();
//                showFailureMessage();
//            }




//    }
//
//    private void showFailureMessage() {
//        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showUnsuccessfulMessage() {
//        mErrorTextView.setText("Something went wrong. Please try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showmBuy() {
//        mItemListView.setVisibility(View.VISIBLE);
//
//
//    }
//
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//    }
