package com.example.ihahire.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.Constants;
import com.example.ihahire.R;
import com.example.ihahire.adapters.FirebaseShopViewHolder;
import com.example.ihahire.models.Shop;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedShopListActivity extends AppCompatActivity {


    private DatabaseReference shopReference;
    private FirebaseRecyclerAdapter<Shop, FirebaseShopViewHolder> shopFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);
        
        shopReference= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PRODUCTS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Shop> options =
                new FirebaseRecyclerOptions.Builder<Shop>()
                        .setQuery(shopReference, Shop.class)
                        .build();

        shopFirebaseAdapter = new FirebaseRecyclerAdapter<Shop, FirebaseShopViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseShopViewHolder firebaseShopViewHolder, int position, @NonNull Shop shop) {
                firebaseShopViewHolder.bindShop(shop);
            }

            @NonNull
            @Override
            public FirebaseShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_buy_list, parent, false);
                return new FirebaseShopViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(shopFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        shopFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(shopFirebaseAdapter!= null) {
            shopFirebaseAdapter.stopListening();
        }
    }
}
