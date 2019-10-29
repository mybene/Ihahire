package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.R;
import com.example.ihahire.adapters.BuyListAdapter;
import com.example.ihahire.models.Shop;
import com.example.ihahire.services.YelpService;

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

    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private BuyListAdapter buyAdapter;

    public List<Shop> products;


    private SharedPreferences looked;
    private SharedPreferences.Editor edited;
    private String recentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String product = intent.getStringExtra("name");

        getProduct(product);

        looked = PreferenceManager.getDefaultSharedPreferences(this);
        recentProduct = looked.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (recentProduct != null) {
            getProduct(recentProduct);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        looked = PreferenceManager.getDefaultSharedPreferences(this);
        edited = looked.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getProduct(query);
                return false;
            }

            private void addToSharedPreferences(String query) {
                edited.putString(Constants.PREFERENCES_LOCATION_KEY, query).apply();
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void getProduct(String product) {

        final YelpService looking = new YelpService();
        looking.findShops(product, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                products = looking.processResults(response);


                BuyActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        buyAdapter = new BuyListAdapter(products, BuyActivity.this);
                        mRecyclerView.setAdapter(buyAdapter);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BuyActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);


                    }
                });
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


//        private void addToSharedPreferences (product){
//            edited.putString(Constants.PREFERENCES_LOCATION_KEY, product).apply();
//        }
    }
}
