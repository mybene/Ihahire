package com.example.ihahire.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.ihahire.R;
import com.example.ihahire.models.Shop;
import com.example.ihahire.ui.BuyDetailActivity;
import com.example.ihahire.ui.SavedShopListActivity;
import com.example.ihahire.util.ItemTouchHelperAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;


public class FirebaseShopListAdapter extends FirebaseRecyclerAdapter<Shop, FirebaseShopViewHolder> implements ItemTouchHelperAdapter {
        private DatabaseReference cakeRef;
        private SavedShopListActivity shopOnStartDragListener;
        private Context mContext;

        private ChildEventListener shopChildEventListener;
        private ArrayList<Shop>products= new ArrayList<>();
        private FirebaseShopViewHolder viewHolder;
        private Shop model;
        private int position;

        public FirebaseShopListAdapter(FirebaseRecyclerOptions<Shop> options,
                               DatabaseReference cakeRef,
                               SavedShopListActivity onStartDragListener,
                               Context context){
        super(options);
        cakeRef = cakeRef.getRef();
        shopOnStartDragListener = onStartDragListener;
        mContext = context;

        shopChildEventListener=cakeRef.addChildEventListener(new ChildEventListener(){

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        products.add(dataSnapshot.getValue(Shop.class));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

        });
        }

        @Override
        protected void onBindViewHolder(@NonNull FirebaseShopViewHolder firebaseShopViewHolder, int position, @NonNull Shop product) {
        firebaseShopViewHolder.bindShop(product);
        }

        @NonNull
        @Override
        public FirebaseShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item_drag, parent, false);
        return new FirebaseShopViewHolder(view);
        }



        @Override
        public boolean onItemMove(int fromPosition, int toPosition){
                Collections.swap(products, fromPosition, toPosition);
                notifyItemMoved(fromPosition, toPosition);
                setIndexInForebase();
                return false;
        }

        private void setIndexInForebase() {
                for (Shop shop : products) {
                        int index = products.indexOf(shop);
                        DatabaseReference ref = getRef(index);
                        shop.setIndex(Integer.toString(index));
                        ref.setValue(shop);
                }
        }

        @Override
        public void stopListening() { super.stopListening(); cakeRef.removeEventListener(shopChildEventListener); }

        @Override
        public void onItemDismiss(int position){
                products.remove(position);
                getRef(position).removeValue();
        }



        @Override
        public void onBindViewHolder(final FirebaseShopViewHolder viewHolder, Shop model, int position) {
                viewHolder.bindShop(model);

                viewHolder.shopPicture.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                                        shopOnStartDragListener.onStartDrag(viewHolder);
                                }
                                return false;
                        }

                });

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(mContext, BuyDetailActivity.class);
                                intent.putExtra("position", viewHolder.getAdapterPosition());
                                intent.putExtra("restaurants", Parcels.wrap(products));
                                mContext.startActivity(intent);
                        }
                });


}
}