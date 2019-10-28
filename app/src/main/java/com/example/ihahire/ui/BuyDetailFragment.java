package com.example.ihahire.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ihahire.R;
import com.example.ihahire.models.Business;
import com.example.ihahire.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyDetailFragment extends Fragment {
    @BindView(R.id.marketImageView)
    ImageView marketImage;
    @BindView(R.id.phoneTextView) TextView phone;
    @BindView(R.id.ratingTextView) TextView rate;
    @BindView(R.id.placeTextView) TextView shop;
    @BindView(R.id.addressTextView)TextView place;
    @BindView(R.id.priceTextView)TextView price;
    @BindView(R.id.productNameTextView) TextView mCategoriesLabel;
    @BindView(R.id.bookedProductsButton) Button save;


    private Business mBuy;


    public BuyDetailFragment() {
        // Required empty public constructor
    }

    public  static  BuyDetailFragment newInstance(Business mBuy){
        BuyDetailFragment buyDetailFragment= new BuyDetailFragment();
        Bundle args= new Bundle();
        args.putParcelable("mBuy", Parcels.wrap(mBuy));
        buyDetailFragment.setArguments(args);
        return buyDetailFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBuy=Parcels.unwrap(getArguments().getParcelable("mBuy"));

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

        mCategoriesLabel.setText(TextUtils.join(", ", categories));
        rate.setText(Double.toString(mBuy.getRating())+"/5");
        price.setText(mBuy.getPrice().toString().toUpperCase());
        phone.setText(mBuy.getPhone());
        shop.setText(mBuy.getLocation().toString());

        return  view;


    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s %s", this.shop,this.rate, this.price,  this.phone,this.mCategoriesLabel,this.place);
    }


}


