package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BuyMainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = BuyMainActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.name) EditText mName;
    @BindView(R.id.lookingButton) Button mLookingButton;
    @BindView(R.id.productList) ListView productslisted;


    private String[] products = new String[]{"Protex", "CarrotLigth", "Whol-WHeat Bread", "White chocolate", "Printer HP", "Baby Daiper", "Protex",
            "CarrotLigth","Whol-WHeat Bread", "White chocolate", "Printer HP", "Baby Daiper"};

    private String[] shops = new String[]{"Frulep", "Simba Supermarket", "La Galette", "KIME Supermarket", "German Butchery", "Meru", "Frulep", "Simba Supermarket", "La Galette",
            "KIME Supermarket", "German Butchery", "Meru"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buy);

        ButterKnife.bind(this);



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mLookingButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v==mLookingButton){
            String product=mName.getText().toString();
            if(!(product).equals("")) {
                addToSharedPreferences(product);
            }

            Intent intent= new Intent(BuyMainActivity.this,BuyListActivity.class);
            intent.putExtra("item",product);
            startActivity(intent);
        }

    }

    private void addToSharedPreferences(String item) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, item).apply();
    }
}


