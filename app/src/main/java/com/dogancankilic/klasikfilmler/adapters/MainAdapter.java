package com.dogancankilic.klasikfilmler.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dogancankilic.klasikfilmler.model.Model;
import com.dogancankilic.klasikfilmler.R;
import com.dogancankilic.klasikfilmler.R.*;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.squareup.picasso.Picasso;

import java.util.List;



public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>  {


    private InterstitialAd mInterstitialAd;
    private Context mContext;
    private List<Model> mData;
    RequestOptions option;





    public MainAdapter(Context mContext, List<Model> mData) {
        this.mContext = mContext;
        this.mData = mData;





        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);



    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.movie_row_item,parent,false);



        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());
        holder.tv_category.setText(mData.get(position).getCategory());





        // Load Image from the internet and set it into Imageview using Glide
        /*Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);*/

        // Load Image from the internet and set it into Imageview using Picasso
        Picasso.get().load(mData.get(position).getImage_url()).error(drawable.loading_shape).fit().into(holder.img_thumbnail);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mData.get(position).getMovie_link()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("force_fullscreen", true);
                mContext.startActivity(intent);

                // For Interstitial Ad

                mInterstitialAd = new InterstitialAd(mContext);
                mInterstitialAd.setAdUnitId("ca-app-pub-6708825800902548/3635861718");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());




                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                        mInterstitialAd.show();
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Code to be executed when an ad request fails.
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onAdClosed() {
                        mContext.startActivity(intent);
                    }
                });




            }
        });


    }



    @Override
    public int getItemCount() {
        return mData.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {




        TextView tv_name;
        TextView tv_rating;
        TextView tv_studio;
        TextView tv_category;
        ImageView img_thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.movie_name);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_category = itemView.findViewById(R.id.category);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail =itemView.findViewById(R.id.thumbnail);






        }
    }
}
