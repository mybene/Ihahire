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
import com.example.ihahire.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseShopViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {



    View seen;
    Context mContext;
    public ImageView shopPicture;



    public FirebaseShopViewHolder(View itemView) {
        super(itemView);
        seen = itemView;
        mContext = itemView.getContext();
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
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }



}
