package com.example.ihahire.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.R;
import com.example.ihahire.models.Business;
import com.example.ihahire.ui.BuyDetail;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyListAdapter  extends RecyclerView.Adapter<BuyListAdapter.BuyViewHolder> {
    private List<Business> mBuy;
    private Context mContext;



    public BuyListAdapter(Context context, List<Business> products) {
        mContext = context;
        mBuy = products;
    }

    @Override
    public BuyListAdapter.BuyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_buy_list, parent, false);
        BuyViewHolder viewHolder = new BuyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BuyListAdapter.BuyViewHolder holder, int position) {
        holder.bindBuy(mBuy.get(position));
    }

    @Override
    public int getItemCount() {
        return mBuy.size();
    }

    public class BuyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.phoneTextView) TextView phone;
        @BindView(R.id.ratingTextView) TextView rate;
        @BindView(R.id.placeTextView) TextView shop;
        @BindView(R.id.priceTextView)TextView price;
        @BindView(R.id.productNameTextView) TextView mCategoriesLabel;

        private Context mContext;

        public BuyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BuyDetail.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("products", Parcels.wrap(mBuy));
            mContext.startActivity(intent);
        }

        public void bindBuy(Business buy) {
            shop.setText(buy.getName());
            mCategoriesLabel.setText(buy.getCategories().get(0).getTitle());
            rate.setText("Rating: " + buy.getRating() + "/5");
            phone.setText(" Call on : "+buy.getPhone());
            shop.setText("Available at :"+ buy.getLocation());



        }
    }
}