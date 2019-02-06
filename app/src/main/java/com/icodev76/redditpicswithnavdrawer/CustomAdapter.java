package com.icodev76.redditpicswithnavdrawer;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.icodev76.redditpicswithnavdrawer.fragment.Fragment_webview;

import com.icodev76.redditpicswithnavdrawer.model.children.Children;
import java.util.List;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{


    private List<Children> dataList;
    private Context context;

    //**************************************

    public interface OnItemCLickListener{
        void OnItemClick(int position);
    }

    private OnItemCLickListener mOnItemCLickListener;

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.mOnItemCLickListener = onItemCLickListener;
    }

    //*****************************************

    public CustomAdapter(Context context, List<Children> dataList){

        this.context = context;
        this.dataList = dataList;
    }


     class CustomViewHolder extends RecyclerView.ViewHolder {

         final View mView;

        //TextView txtTitle;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            //txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);

            //********************************************************

            if(mOnItemCLickListener!=null){
                coverImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemCLickListener.OnItemClick(getAdapterPosition());
                    }
                });
            }
            //***************************************************88

        }

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        Glide
                .with(context)
                .load(dataList.get(position).getData().getUrl())//.getData().getUrl())
                        .apply(fitCenterTransform()
                        .placeholder(R.color.colorPrimaryDark)
                        )
                        .into(holder.coverImage);
                        //.error(R.drawable.error)

        /*//*******************************8
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked : "+dataList.get(position).getData().getUrl(), Toast.LENGTH_SHORT).show();
                String url=dataList.get(position).getData().getUrl();
                //showWebViewFragment(url);

            }
        });*/


    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

