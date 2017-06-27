package com.example.geniusplaza.vocabularyset.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.ApiConstants;
import com.example.geniusplaza.vocabularyset.EditVocabSet;
import com.example.geniusplaza.vocabularyset.POJO.AddWordResponse;
import com.example.geniusplaza.vocabularyset.POJO.EditWordBody;
import com.example.geniusplaza.vocabularyset.POJO.EditWordResponse;
import com.example.geniusplaza.vocabularyset.POJO.Word;
import com.example.geniusplaza.vocabularyset.POJO.WordsResource;
import com.example.geniusplaza.vocabularyset.R;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;
import com.example.geniusplaza.vocabularyset.VocabDashboard;
import com.example.geniusplaza.vocabularyset.WordsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geniusplaza on 6/21/17.
 */

//RecyclerView Adapter to populate EditVocab set Recyclerview.
//'row_recycler' layout is populated in each row and it is also used to add a new word.
public class AddWordAdapter extends RecyclerView.Adapter<AddWordAdapter.ViewHolder> {

    private WordsResource vocabSet = new WordsResource();
    private Context mContext;
    String word, meaning, sentence, typeId;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Word vocabularySetContent = vocabSet.getWords().get(position);


        if(vocabSet.getWords() != null){
            holder.wordName.setText(vocabularySetContent.getName());
            holder.wordMeaning.setText(vocabularySetContent.getMeaning());
            holder.wordSentence.setText(vocabularySetContent.getSentence());
            holder.wordOrder.setText(String.valueOf(vocabularySetContent.getOrder()));
            holder.wordOrder.setClickable(false);
            holder.wordOrder.setFocusable(false);
            holder.editWord.setVisibility(View.VISIBLE);
            holder.deleteWord.setVisibility(View.VISIBLE);
            holder.deleteWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    //Api calls to delete single words.
                    RestClient.getExampleApi().deleteVocabWord("Bearer " + ApiConstants.accessToken, vocabSet.getWords().get(position).getId().toString())
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<AddWordResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddWordResponse value) {
                            Toast.makeText(v.getContext()," Word succesfuly deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(v.getContext(), VocabDashboard.class);
                            v.getContext().startActivity(i);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(v.getContext(),"Not permitted",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            });

            Log.d("Word Id: ", vocabSet.getWords().get(position).getId().toString());
            holder.editWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    //Api call to edit word
                    EditWordBody editWordBody = new EditWordBody(holder.wordName.getText().toString(), holder.wordMeaning.getText().toString(), holder.wordSentence.getText().toString(), "2");
                    Log.d(TAG, "onClick: " + editWordBody.getWord());
                    RestClient.getExampleApi().editVocabWord("Bearer " + ApiConstants.accessToken, vocabSet.getWords().get(position).getId().toString(), editWordBody)
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<EditWordResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(EditWordResponse value) {
                            Log.d("Word: ", holder.wordName.getText().toString());
                            Log.d("Success", "Anything");
                            Toast.makeText(v.getContext(),"Word Successfully Edited!", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(v.getContext(), "Not permitted", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            });

        }
        else{
            holder.wordName.setText("Enter the word");
            holder.wordMeaning.setText("Enter the Meaning");
            holder.wordSentence.setText("Enter the sentence");
            holder.wordOrder.setText("Enter the order");
            holder.editWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(((EditVocabSet) mContext).getApplicationContext(), "enter the words", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public EditText wordName, wordMeaning, wordSentence, wordOrder;
        Spinner wordType;
        FloatingActionButton deleteWord, editWord;

        public ViewHolder(View v) {
            super(v);
            wordName = (EditText) v.findViewById(R.id.textViewAddWord);
            wordMeaning = (EditText) v.findViewById(R.id.textViewAddMeaning);
            wordSentence = (EditText) v.findViewById(R.id.textViewAddSentence);
            wordOrder =(EditText) v.findViewById(R.id.textViewOrder);
            wordType = (Spinner) v.findViewById(R.id.spinnerWordType);
            deleteWord = (FloatingActionButton) v.findViewById(R.id.vocabDeleteVocabWord);
            editWord = (FloatingActionButton) v.findViewById(R.id.vocabEditVocabWord);

            List<String> lang = new ArrayList<String>();
            String[] langItems = new String []{"Noun", "Verb", "Adjective", "Adverb", "Conjuction", "Abbreviation", "Exclamation", "Preposition", "Pronoun", "Article", "Determiner"};
            ArrayAdapter<String> myadapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, langItems);
            myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            wordType.setAdapter(myadapter);
        }
    }

}