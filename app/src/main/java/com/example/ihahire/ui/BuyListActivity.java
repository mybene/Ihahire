package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.R;
import com.example.ihahire.adapters.BuyListAdapter;
import com.example.ihahire.models.Shop;
import com.example.ihahire.services.YelpService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyListActivity extends AppCompatActivity  {


    public static final String TAG = BuyListActivity.class.getSimpleName();

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private SharedPreferences looked;
    private SharedPreferences.Editor edited;
    private String recentProducts;


    private BuyListAdapter buyAdapter;

    public ArrayList<Shop> products=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        ButterKnife.bind(this);


        Intent intent = getIntent();
        String product = intent.getStringExtra("product");
        getShops(product);

    }

    private void getShops(String product) {
        final YelpService yelpService = new YelpService();
        yelpService.findShops(product, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                products = (ArrayList<Shop>) yelpService.processResults(response);
                BuyListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buyAdapter = new BuyListAdapter(getApplicationContext(), (ArrayList<Shop>) products);
                        mRecyclerView.setAdapter(buyAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BuyListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                showFailureMessage();

            }

            private void showFailureMessage() {
                mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
                mErrorTextView.setVisibility(View.VISIBLE);
            }


        });

    }



}