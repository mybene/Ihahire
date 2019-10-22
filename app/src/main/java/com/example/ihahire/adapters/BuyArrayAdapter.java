package com.example.ihahire.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.ihahire.ui.BuyListActivity;


public class BuyArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private  String[] mProducts;
    private  String[] mLocation;


    public BuyArrayAdapter(@NonNull BuyListActivity context, int resource, String[] mLocation, String[] mProducts) {
        super((Context) context, resource);
        this.mContext = mContext;
        this.mProducts = mProducts;
        this.mLocation = mLocation;
    }

    @Override
    public Object getItem(int position){
        String products=mProducts[position];
        String location=mLocation[position];
        return String.format("%s \n Could be found at:%s",location,products);
   }


   @Override
    public int getCount(){
        return mProducts.length;
   }
}