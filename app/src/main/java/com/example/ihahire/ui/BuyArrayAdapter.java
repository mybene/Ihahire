package com.example.ihahire.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class BuyArrayAdapter  extends ArrayAdapter {


    private Context display;
    private String[] items;
    private String[] images;


    public BuyArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, Context display, String[] items, String[] images) {
        super(context, resource, textViewResourceId);
        this.display = display;
        this.items = items;
        this.images = images;
    }

    @Override
    public Object getItem(int position){
        return null;
    }


    @Override
    public long getItemId(int position){
        return 0;
    }
}
