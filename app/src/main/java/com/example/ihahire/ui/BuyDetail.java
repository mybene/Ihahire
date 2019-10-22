package com.example.ihahire.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ihahire.R;
import com.example.ihahire.adapters.BuyPagerAdapter;
import com.example.ihahire.models.Business;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;

public class BuyDetail extends AppCompatActivity {

    @BindView(R.id.vpPager) ViewPager mViewPager;
    private BuyPagerAdapter adapterViewPager;
    List<Business> mBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_detail);


        mBuy = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BuyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mBuy);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
