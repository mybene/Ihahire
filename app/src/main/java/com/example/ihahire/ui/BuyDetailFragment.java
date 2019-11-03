package com.example.ihahire.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ihahire.Constants;
import com.example.ihahire.R;
import com.example.ihahire.models.Business;
import com.example.ihahire.models.Category;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyDetailFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.brandImg) ImageView marketImage;
    @BindView(R.id.phoneTextView) TextView phone;
    @BindView(R.id.ratingTextView) TextView rate;
    @BindView(R.id.shopName) TextView shop;
    @BindView(R.id.PlaceTextView) TextView website;
    @BindView(R.id.addressTextView)TextView place;
    @BindView(R.id.itemName) TextView mCategoriesLabel;
    @BindView(R.id.bookedProductsButton) Button save;


    private Business mBuy;


    public BuyDetailFragment() {
        // Required empty public constructor
    }

    public  static  BuyDetailFragment newInstance(Business shop){
        BuyDetailFragment buyDetailFragment= new BuyDetailFragment();
        Bundle args= new Bundle();
        args.putParcelable("mBuy", Parcels.wrap(shop));
        buyDetailFragment.setArguments(args);
        return buyDetailFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBuy=Parcels.unwrap(getArguments().getParcelable("shop"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_buydetail,container,false);

        ButterKnife.bind(this,view);

        Picasso.get().load(mBuy.getImageUrl()).into(marketImage);

        List<String> categories= new ArrayList<>();

        for(Category category:mBuy.getCategories()){
            categories.add(category.getTitle());
        }


        shop.setText(mBuy.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ",mBuy.getCategories()));
        rate.setText(Double.toString(mBuy.getRating())+"/5");
        phone.setText(mBuy.getPhone());
        place.setText(mBuy.getLocation().toString());

        shop.setOnClickListener(this);
        place.setOnClickListener(this);
        phone.setOnClickListener(this);
        save.setOnClickListener(this);

        return  view;


    }



    @Override
    public void onClick(View v) {
        if(v==website){
            Intent web= new Intent(Intent.ACTION_WEB_SEARCH,Uri.parse(mBuy.getUrl()));
            startActivity(web);
        }
        if(v==place){
            Intent location=new Intent(Intent.ACTION_VIEW,Uri.parse("location: "+mBuy.getCoordinates().getLongitude()+
                    ", "+mBuy.getCoordinates().getLatitude()+"?q=("+mBuy.getName()));
            startActivity(location);
        }


        if(v==phone){
            Intent phoneIntent= new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+mBuy.getPhone()));
            startActivity(phoneIntent);
        }
        if(v==save){
            FirebaseUser data = FirebaseAuth.getInstance().getCurrentUser();
            String uid = data.getUid();

            DatabaseReference cakeRef= FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PRODUCTS).child(uid);

            DatabaseReference pushRef=cakeRef.push();
            String pushId=pushRef.getKey();

            cakeRef.setValue(mBuy);
            Toast.makeText(getContext(),"saved",Toast.LENGTH_SHORT).show();
        }

    }
}



