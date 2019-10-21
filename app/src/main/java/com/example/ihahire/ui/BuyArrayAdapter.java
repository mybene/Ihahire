package com.example.ihahire.ui;

import android.content.Context;
import android.widget.ArrayAdapter;


public class BuyArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private  String[] mProducts;
    private  String[] mLocation;

    public BuyArrayAdapter(Context context, int resource, String[] mProducts, String[] mLocation) {
        super(context, resource);
        this.mContext = mContext;
        this.mProducts = mProducts;
        this.mLocation = mLocation;
    }




    @Override
    public Object getItem(int position){
        String products=mProducts[position];
        String location=mLocation[position];
        return String.format("%s \n Could be found at:%s",products,location);
   }


   @Override
    public int getCount(){
        return mProducts.length;
   }
}