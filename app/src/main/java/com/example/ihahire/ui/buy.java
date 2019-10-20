package com.example.ihahire.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihahire.R;
import com.example.ihahire.models.Business;
import com.example.ihahire.models.Category;
import com.example.ihahire.models.Search;
import com.example.ihahire.networks.YelpApi;
import com.example.ihahire.networks.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class buy extends AppCompatActivity {

    @BindView(R.id.productList) ListView mProductList;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private String[] products = new String[]{"Cabbage", "Carrot", "Lengalenga", "Brocoli", "Persil", "Eggplant", "Coniflower", "Black bean", "Peas", "Lentil", "Potatoes", "Pumpinks",
            "Sweet potatoes", "Garlic", "Basil", "Coriander", "Parsely", "Lettuce", "Peppers", "Tomatoes"};

    private String[] location = new String[]{"Frulep", "Simba Supermarket", "La Galette", "KIME Supermarket", "German Butchery"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        ButterKnife.bind(this);

        ArrayAdapter tobuy = new ArrayAdapter(this, android.R.layout.simple_list_item_1, products);
        mProductList.setAdapter(tobuy);

        mProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String products = ((TextView) view).getText().toString();
                String location = ((TextView) view).getText().toString();

                Toast.makeText(buy.this, location, Toast.LENGTH_LONG).show();
            }
        });


        YelpApi client = YelpClient.getClient();

        Call<Search> call = client.getProducts(location, "products");

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                hideProgressBar();
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

                    ArrayAdapter tobuy = new BuyArrayAdapter(buy.this, android.R.layout.activity_list_item, articles, categories);
                    mProductList.setAdapter(tobuy);

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

        private void showRestaurants() {
            mProductList.setVisibility(View.VISIBLE);
            mTitle.setVisibility(View.VISIBLE);
        }

        private void hideProgressBar() {
            mProgressBar.setVisibility(View.GONE);
        }
    }



