package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class BuyActivity extends AppCompatActivity {


    public static final String TAG = BuyActivity.class.getSimpleName();

//
//    @BindView(R.id.itemListView)ListView mItemListView;

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private BuyListAdapter buyAdapter;

    public List<Business> products;


    private SharedPreferences mSharedPreferences;
    private String mRecentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        ButterKnife.bind(this);



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentProduct = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentProduct != null) {
            getProduct(mRecentProduct);
        }
    }

        private void getProduct(String mRecentProduct) {

            Intent intent = getIntent();
            String location = intent.getStringExtra("item");

            YelpApi client = YelpClient.getClient();

            Call<Search> call = client.getProducts(location, "products");

            call.enqueue(new Callback<Search>() {
                @Override
                public void onResponse(Call<Search> call, Response<Search> response) {
                    hideProgressBar();

                    if (response.isSuccessful()) {
                    products = response.body().getBusinesses();


                        buyAdapter=new BuyListAdapter(products, BuyActivity.this);
                        mRecyclerView.setAdapter(buyAdapter);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BuyActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        showmBuy();
                    } else {
                        showUnsuccessfulMessage();
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

         private void showmBuy() {
         mRecyclerView.setVisibility(View.VISIBLE);


        }

        private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
       }
}
