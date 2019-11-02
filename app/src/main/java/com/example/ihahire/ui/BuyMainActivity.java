package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.Constants;
import com.example.ihahire.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BuyMainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = BuyMainActivity.class.getSimpleName();


    private SharedPreferences looked;
    private SharedPreferences.Editor edited;
    private String recentProduct;

//    add eventlistener in to saved the search on firebase
    private DatabaseReference searchedProductReference;
    private ValueEventListener searchedProductReferenceListener;

    @BindView(R.id.name) EditText mName;
    @BindView(R.id.lookingButton) Button mLookingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        searchedProductReference= FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buy);

        ButterKnife.bind(this);

        mLookingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mLookingButton){

            String product=mName.getText().toString();
            saveProductToFirebase(product);

            if(!(product).equals("")){
                addToSharePreferences(product);
            }

            Intent intent= new Intent(BuyMainActivity.this, BuyListActivity.class);
            intent.putExtra("product",product);
            startActivity(intent);
        }

    }

    private void addToSharePreferences(String product) {
        edited.putString(Constants.PREFERENCES_LOCATION_KEY,product).apply();
    }

    private void saveProductToFirebase(String product) {
        searchedProductReference.push().setValue(product);

    }


}


