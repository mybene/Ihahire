package com.example.ihahire.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ihahire.R;
import com.example.ihahire.models.Shop;
import com.example.ihahire.ui.BuyDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyListAdapter  extends RecyclerView.Adapter<BuyListAdapter.BuyViewHolder> {
//    private List<Shop> mBuy;
    private Context mContext;
    private ArrayList<Shop>mBuy=new ArrayList<>();


    public BuyListAdapter(Context mContext, ArrayList<Shop> mBuy) {
        this.mContext = mContext;
        this.mBuy = mBuy;
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


        @BindView(R.id.ratingTextView) TextView rate;
        @BindView(R.id.shopName) TextView shop;
        @BindView(R.id.specialite)TextView specilaity;
        @BindView(R.id.picture) ImageView shopImage;
        private Context mContext;

        public BuyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }
        public void bindBuy(Shop buy) {
            Picasso.get().load(buy.getImageUrl()).into(shopImage);
            specilaity.setText(buy.getCategories().get(0));
            rate.setText("Rating: " + buy.getRating() + "/5");
            shop.setText("Available at :"+ buy.getName());

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BuyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("products", Parcels.wrap(mBuy));
            mContext.startActivity(intent);
        }


    }
}