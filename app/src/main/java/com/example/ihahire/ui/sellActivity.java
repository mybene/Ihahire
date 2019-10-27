package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;

import butterknife.BindView;
import butterknife.ButterKnife;



public class sellActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = sellActivity.class.getSimpleName();

  @BindView(R.id.name) EditText mName;
  @BindView(R.id.place) EditText shop;
  @BindView(R.id.phone) EditText contact;
  @BindView(R.id.selling) Button mSelling;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();


        mSelling.setOnClickListener(this);


    }


    @Override
    public   void onClick(View mSelling) {


            String product = mName.getText().toString();
            String shop = mName.getText().toString();
            String phone = contact.getText().toString();

            if((!(product).equals(""))&& (!(shop).equals(""))&&(!(phone).equals(""))){
                addToSharedPreferences(product);
            }


            Toast.makeText(sellActivity.this, "Your product is received!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(sellActivity.this, newItemsActivity.class);
             intent.putExtra("name", product);
             intent.putExtra("shop", shop);
             intent.putExtra("phone", phone);
            startActivity(intent);


    }

    private void addToSharedPreferences(String product){
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, product).apply();
    }
}






