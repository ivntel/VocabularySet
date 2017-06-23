package com.example.geniusplaza.vocabularyset.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.geniusplaza.vocabularyset.ApiConstants;
import com.example.geniusplaza.vocabularyset.EditVocabSet;
import com.example.geniusplaza.vocabularyset.POJO.Word;
import com.example.geniusplaza.vocabularyset.POJO.WordsResource;
import com.example.geniusplaza.vocabularyset.R;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;
import com.example.geniusplaza.vocabularyset.VocabDashboard;
import com.example.geniusplaza.vocabularyset.WordsDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geniusplaza on 6/21/17.
 */

public class AddWordAdapter extends RecyclerView.Adapter<AddWordAdapter.ViewHolder> {


    private WordsResource vocabSet = new WordsResource();
    private Context mContext;

    public static final String TAG = AddWordAdapter.class.getSimpleName();

    public AddWordAdapter(Context context, List<Word> vocabList) {
        mContext = context;
        vocabSet.setWords(vocabList);

    }

    public void updateData(List<Word> vocabList) {
        vocabSet.setWords(vocabList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return vocabSet.getWords().size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    //Replace contents of a view(invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Word vocabularySetContent = vocabSet.getWords().get(position);


        if(vocabSet.getWords() != null){
            holder.wordName.setText(vocabularySetContent.getName());
            holder.wordMeaning.setText(vocabularySetContent.getMeaning());
            holder.wordSentence.setText(vocabularySetContent.getSentence());
            holder.wordOrder.setText(String.valueOf(vocabularySetContent.getOrder()));

        }
        else{
            holder.wordName.setText("Enter the word");
            holder.wordMeaning.setText("Enter the Meaning");
            holder.wordSentence.setText("Enter the sentence");
            holder.wordOrder.setText("Enter the order");
        }

    }
    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        EditText wordName, wordMeaning, wordSentence, wordOrder;
        Spinner wordType;

        public ViewHolder(View v) {
            super(v);
            wordName = (EditText) v.findViewById(R.id.textViewAddWord);
            wordMeaning = (EditText) v.findViewById(R.id.textViewAddMeaning);
            wordSentence = (EditText) v.findViewById(R.id.textViewAddSentence);
            wordOrder =(EditText) v.findViewById(R.id.textViewOrder);
            wordType = (Spinner) v.findViewById(R.id.spinnerWordType);

            List<String> lang = new ArrayList<String>();
            String[] langItems = new String []{"Noun", "Verb", "Adjective", "Adverb", "Conjuction", "Abbreviation", "Exclamation", "Preposition", "Pronoun", "Article", "Determiner"};
            ArrayAdapter<String> myadapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, langItems);
            myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            wordType.setAdapter(myadapter);
        }
    }

}