package com.example.geniusplaza.vocabularyset.Utils;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.geniusplaza.vocabularyset.POJO.Resource;
import com.example.geniusplaza.vocabularyset.POJO.ResourceNew;
import com.example.geniusplaza.vocabularyset.POJO.Resources;
import com.example.geniusplaza.vocabularyset.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geniusplaza on 6/14/17.
 */

public class VocabDashGridviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ResourceNew> mResources;
    private Context mContext;

    public VocabDashGridviewAdapter(List<ResourceNew> mResources, Context mContext) {
        this.mResources = mResources;
        this.mContext = mContext;
    }

    public void updateData(Resources resources) {
        mResources = resources.getResources();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ResourceNew getItem(int position) {
        return mResources.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;

        final ResourceNew resourceNew = mResources.get(position);

        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gridview, parent, false);
            holder = new ViewHolder();

            holder.userTextView = (TextView) view.findViewById(R.id.vocabDashUserName);
            holder.descriptionTextView = (TextView) view.findViewById(R.id.vocabDashDescription);
            holder.titleTextView = (TextView) view.findViewById(R.id.vocabDashTitleTextView);
            holder.vocabSetImageView = (ImageView) view.findViewById(R.id.vocabSetImage);
            holder.userIconImageView = (ImageView) view.findViewById(R.id.vocabDashUserIcon);
            holder.vocabLayout = (ConstraintLayout)view.findViewById(R.id.vocabDashLayout);

            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        holder.userTextView.setText(resourceNew.getCreatorUsername());
        holder.descriptionTextView.setText(resourceNew.getDescription());
        holder.titleTextView.setText(resourceNew.getTitle());
        Glide.with(mContext).load(resourceNew.getResourceImage()).into(holder.vocabSetImageView);
        Glide.with(mContext).load(resourceNew.getIcon()).into(holder.userIconImageView);

        /*holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.ARG_MOVIE, movie);
                mContext.startActivity(intent);
            }
        });*/


        return view;
    }

    public static class ViewHolder {

        ConstraintLayout vocabLayout;
        TextView userTextView, descriptionTextView, titleTextView;
        ImageView vocabSetImageView, userIconImageView;

    }
}
