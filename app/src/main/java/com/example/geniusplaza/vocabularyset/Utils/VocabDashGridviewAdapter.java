package com.example.geniusplaza.vocabularyset.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.ApiConstants;
import com.example.geniusplaza.vocabularyset.EditVocabSet;
import com.example.geniusplaza.vocabularyset.POJO.AddWordResponse;
import com.example.geniusplaza.vocabularyset.POJO.AuthToken;
import com.example.geniusplaza.vocabularyset.POJO.ResourceNew;
import com.example.geniusplaza.vocabularyset.POJO.Resources;
import com.example.geniusplaza.vocabularyset.R;
import com.bumptech.glide.Glide;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;
import com.example.geniusplaza.vocabularyset.ShowActivity;
import com.example.geniusplaza.vocabularyset.VocabDashboard;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.geniusplaza.vocabularyset.ApiConstants.refreshToken;

/**
 * Created by geniusplaza on 6/14/17.
 */

public class VocabDashGridviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    public List<ResourceNew> mResources;
    private Context mContext;
    public VocabDashboard vocabDashboard = new VocabDashboard();

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
            holder.takeTest = (Button)view.findViewById(R.id.vocabDashTakeTestButton);
            holder.delete = (FloatingActionButton) view.findViewById(R.id.vocabDashDeleteFB);
            EditVocabSet.resourceId = mResources.get(position).getId().toString();
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        holder.userTextView.setText(resourceNew.getCreatorUsername());
        holder.descriptionTextView.setText(resourceNew.getDescription());
        holder.titleTextView.setText(resourceNew.getTitle());

        if(resourceNew.getResourceImage() != ""){
            Glide.with(mContext).load(resourceNew.getResourceImage()).into(holder.vocabSetImageView);
        }
        if(resourceNew.getIcon() != "") {
            Glide.with(mContext).load(resourceNew.getIcon()).into(holder.userIconImageView);
        }
        //Glide.with(mContext).load(resourceNew.getResourceImage()).into(holder.vocabSetImageView);
        //Glide.with(mContext).load(resourceNew.getIcon()).into(holder.userIconImageView);
        holder.takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ffffffff", (String.valueOf(position)));
                Log.d("Button clicked",mResources.get(position).getId().toString());
                ShowActivity.resId = mResources.get(position).getId().toString();

                Intent i = new Intent(((VocabDashboard) mContext).getApplicationContext(), ShowActivity.class);
                mContext.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowActivity.resId = mResources.get(position).getId().toString();
                RestClient.getExampleApi().deleteVocabSet("Bearer " + ApiConstants.accessToken, mResources.get(position).getId().toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<AddWordResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddWordResponse value) {
                        Toast.makeText(((VocabDashboard) mContext).getApplicationContext(), "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                        //refresh
                        Intent i = new Intent(((VocabDashboard) mContext).getApplicationContext(), VocabDashboard.class);
                        mContext.startActivity(i);
                    }



                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(((VocabDashboard) mContext).getApplicationContext(), "Cannot Delete!",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
        return view;
    }

    public static class ViewHolder {

        ConstraintLayout vocabLayout;
        TextView userTextView, descriptionTextView, titleTextView;
        ImageView vocabSetImageView, userIconImageView;
        Button takeTest;
        FloatingActionButton delete;

    }
}
