package com.example.ihahire.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;


public class BuyArrayAdapter extends ArrayAdapter {

//    private Context mContext;
//    private  String[] mProducts;
//    private  String[] shops;
//
//
//    public BuyArrayAdapter(Context mContext, int resource, String[] shops, String[] mProducts) {
//        super( mContext, resource);
//        this.mContext = mContext;
//        this.mProducts = mProducts;
//        this.shops = shops;
//    }
//
//
//    @Override
//    public Object getItem(int position){
////        String products=mProducts[position];
////        String location=shops[position];
////        return String.format("%s \n Could be found at:%s",location,products);
//        return  null;
//    }
//
//
////   @Override
////    public int getCount(){
////        return mProducts.length;
////   }
//    @Override
//    public void getItemId()


    private  Context seen;
    private  String[] items;
    private  String images;

    public BuyArrayAdapter(@NonNull Context context, int resource, Context seen, String[] products, String images) {
        super(context, resource);
        this.seen = seen;
        this.items = products;
        this.images = images;
    }

    @Override
    public Object getItem(int position){
        return  null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }
}