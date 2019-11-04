package com.example.ihahire.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ihahire.R;
import com.example.ihahire.adapters.BuyPagerAdapter;
import com.example.ihahire.models.Shop;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyDetailActivity extends AppCompatActivity {

    @BindView(R.id.vpPager) ViewPager mViewPager;
    private BuyPagerAdapter adapterViewPager;
    ArrayList<Shop>products=new ArrayList<>();

    private int FragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_detail);

        ButterKnife.bind(this);

        products = Parcels.unwrap(getIntent().getParcelableExtra("products"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BuyPagerAdapter(getSupportFragmentManager(),products);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
