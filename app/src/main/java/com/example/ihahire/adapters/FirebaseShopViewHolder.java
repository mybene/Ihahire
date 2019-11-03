package com.example.ihahire.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.Constants;
import com.example.ihahire.R;
import com.example.ihahire.models.Shop;
import com.example.ihahire.ui.BuyDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View seen;
    Context mContext;

    public FirebaseShopViewHolder(View itemView) {
        super(itemView);
        seen = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindShop(Shop product) {
        ImageView shopPicture = (ImageView) seen.findViewById(R.id.brandImg);
        TextView productName= (TextView) seen.findViewById(R.id.itemName);
        TextView shop = (TextView) seen.findViewById(R.id.PlaceTextView);
        TextView rate = (TextView) seen.findViewById(R.id.ratingTextView);

        Picasso.get().load(product.getImageUrl()).into(shopPicture);

        productName.setText(product.getName());
        shop.setText(product.getCategories().get(0));
        rate.setText("Rating: " + product.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Shop> products = new ArrayList<>();
        DatabaseReference cakeref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PRODUCTS);
        cakeref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    products.add(snapshot.getValue(Shop.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BuyDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("products", Parcels.wrap(products));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
