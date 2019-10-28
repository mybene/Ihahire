package com.example.ihahire.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    //add eventlistener in to saved the search on firebase
    private DatabaseReference searchedProductReference;
    private ValueEventListener searchedProductReferenceListener;

    @BindView(R.id.name) EditText mName;
    @BindView(R.id.lookingButton) Button mLookingButton;



    private String[] products = new String[]{"Protex", "CarrotLigth", "Whol-WHeat Bread", "White chocolate", "Printer HP", "Baby Daiper", "Protex",
            "CarrotLigth","Whol-WHeat Bread", "White chocolate", "Printer HP", "Baby Daiper"};

    private String[] shops = new String[]{"Frulep", "Simba Supermarket", "La Galette", "KIME Supermarket", "German Butchery", "Meru", "Frulep", "Simba Supermarket", "La Galette",
            "KIME Supermarket", "German Butchery", "Meru"};


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



//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mLookingButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v==mLookingButton){
            String product=mName.getText().toString();

            saveProductToFirebase(product);

//            if(!(product).equals("")) {
//                addToSharedPreferences(product);
//            }

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
//    private void addToSharedPreferences(String product) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, product).apply();
//    }
}


