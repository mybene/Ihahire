package com.example.ihahire.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.Constants;
import com.example.ihahire.R;
import com.example.ihahire.adapters.FirebaseShopListAdapter;
import com.example.ihahire.models.Shop;
import com.example.ihahire.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedShopListActivity extends AppCompatActivity {


    private DatabaseReference shopReference;

    private ItemTouchHelper shopItemTouchHelper;

    private FirebaseShopListAdapter shopFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PRODUCTS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);


        FirebaseRecyclerOptions<Shop> options =
                new FirebaseRecyclerOptions.Builder<Shop>()
                        .setQuery(shopReference, Shop.class)
                        .build();

        shopFirebaseAdapter=new FirebaseShopListAdapter(options,shopReference,this,this);

         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(shopFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(shopFirebaseAdapter);
        shopItemTouchHelper = new ItemTouchHelper(callback);
        shopItemTouchHelper.attachToRecyclerView(mRecyclerView);
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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        shopFirebaseAdapter.stopListening(); }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        shopItemTouchHelper.startDrag(viewHolder);
    }
}

