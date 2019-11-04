package com.example.ihahire.util;

import android.annotation.SuppressLint;

import com.example.ihahire.adapters.FirebaseShopViewHolder;
import com.example.ihahire.models.Shop;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);

    @SuppressLint("ClickableViewAccessibility")
    void onBindViewHolder(FirebaseShopViewHolder viewHolder, Shop model, int position);
}
