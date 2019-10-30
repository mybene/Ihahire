package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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



    private String[] products = new String[]{"straberry bavarois","lemon cake","banana cake","chocolate moka cake","cheese cake","fruits tarte","biche noel vanilla","biche noel choco","madelaine"};

    private String[] shops = new String[]{"Frulep", "Simba Supermarket", "La Galette", "KIME Supermarket", "German Butchery", "Meru", "Frulep", "Simba Supermarket", "La Galette",
            "KIME Supermarket"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        searchedProductReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        searchedProductReferenceListener = searchedProductReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buy);

        ButterKnife.bind(this);



        looked = PreferenceManager.getDefaultSharedPreferences(this);
        recentProduct = looked.getString(Constants.PREFERENCES_LOCATION_KEY,null);
        if(recentProduct!=null){
       getProducts(recentProduct);}

        mLookingButton.setOnClickListener(this);
    }

    private void getProducts(String recentProduct) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public void onClick(View v) {
        if(v==mLookingButton){
            String product=mName.getText().toString();

            saveProductToFirebase(product);


            Intent intent= new Intent(BuyMainActivity.this, BuyActivity.class);
            intent.putExtra("item",product);
            startActivity(intent);
        }

    }

    private void saveProductToFirebase(String product) {
        searchedProductReference.push().setValue(product);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchedProductReference.removeEventListener(searchedProductReferenceListener);

    }

}


