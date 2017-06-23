package com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB;

/**
 * Created by geniusplaza on 6/23/17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by geniusplaza on 6/21/17.
 */

public class DBRecyclerViewAdapter extends RecyclerView.Adapter<DBRecyclerViewAdapter.ViewHolder> {


    private ArrayList<HashMap<String,Object>> mWords;
    private Context mContext;

    public static final String TAG = DBRecyclerViewAdapter.class.getSimpleName();

    public DBRecyclerViewAdapter(Context context, ArrayList<HashMap<String,Object>> words) {
        mContext = context;
        mWords = words;

    }

    public void updateData(ArrayList<HashMap<String,Object>> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_database, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    //Replace contents of a view(invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final HashMap<String,Object> word = mWords.get(position);
        holder.wordName.setText(word.get("word").toString());
        holder.wordMeaning.setText(word.get("meaning").toString());
        holder.wordSentence.setText(word.get("sentence").toString());
        holder.wordImage.setImageBitmap(DBBitmapUtility.getImage((byte[]) word.get("image_data")));
        //wordImage.setImageBitmap(DBBitmapUtility.getImage((byte[]) wordList.get(0).get("image_data")));

    }
    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView wordImage;
        TextView wordName, wordMeaning, wordSentence;

        public ViewHolder(View v) {
            super(v);
            wordImage = (ImageView) v.findViewById(R.id.wordDbImageView);
            wordName = (TextView) v.findViewById(R.id.wordDbTextView);
            wordMeaning = (TextView) v.findViewById(R.id.meaningDbTextView);
            wordSentence = (TextView) v.findViewById(R.id.sentenceDbTextView);
        }
    }
}