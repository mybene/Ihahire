package com.example.ihahire.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ihahire.models.Business;
import com.example.ihahire.ui.BuyDetailFragment;

import java.util.List;

public class BuyPagerAdapter extends FragmentPagerAdapter {
    private List<Business> mBuy;

    public BuyPagerAdapter(FragmentManager fm, List<Business> products) {
        super(fm);
        mBuy = products;
    }

    @Override
    public Fragment getItem(int position) {
        return BuyDetailFragment.newInstance(mBuy.get(position));
    }

    @Override
    public int getCount() {
        return mBuy.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBuy.get(position).getName();
    }
}