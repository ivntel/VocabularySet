package com.example.geniusplaza.vocabularyset;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.POJO.Word;
import com.example.geniusplaza.vocabularyset.POJO.WordsResource;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ShowActivity extends AppCompatActivity {

    public TextView word, meaning, sentence,curPos,totalSize;
    public static String resId = null;
    public List<Word> vocabWords = new ArrayList<Word>();
    FloatingActionButton next, prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Log.d("ShowActivity test:", resId);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("access token", pref.getString("access_token", ""));

        word = (TextView) findViewById(R.id.textViewWord);
        meaning = (TextView) findViewById(R.id.textViewMeaning);
        sentence = (TextView) findViewById(R.id.textViewSentence);
        curPos = (TextView)findViewById(R.id.textViewCurPos);
        totalSize = (TextView)findViewById(R.id.textViewSize);
        next = (FloatingActionButton)findViewById(R.id.floatingActionButtonNextWord);
        prev = (FloatingActionButton)findViewById(R.id.floatingActionButtonPreviousWord);
        curPos.setText("1");
        RestClient.getExampleApi().flashcardCreate("Bearer " + pref.getString("access_token", ""), resId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<WordsResource>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WordsResource value) {
                Toast.makeText(getApplicationContext(), "Get Words Successful!", Toast.LENGTH_SHORT).show();
                vocabWords = value.getWords();
                totalSize.setText(String.valueOf(vocabWords.size()));
                if(vocabWords.size() == 0){
                    word.setText("YOU HAVE NO WORDS");
                    meaning.setVisibility(View.GONE);
                    sentence.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    prev.setVisibility(View.GONE);
                }
                else {

                    word.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString())-1).getName());
                    meaning.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString())-1).getMeaning());
                    sentence.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString())-1).getSentence());
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void nextVocabWord(View v){
        next.setClickable(true);
        //tempPos = 1
        int tempPos = Integer.parseInt(curPos.getText().toString());
        // tempSize = 12
        int tempSize = Integer.parseInt(totalSize.getText().toString());
        if (tempPos<tempSize){

            word.setText(vocabWords.get(tempPos).getName());
            meaning.setText(vocabWords.get(tempPos).getMeaning());
            sentence.setText(vocabWords.get(tempPos).getSentence());
            ++tempPos;
            curPos.setText(String.valueOf(tempPos));
        }
        else{
            next.setClickable(false);
        }
    }
    public  void previousVocabWord(View v){
        prev.setClickable(true);
        int tempPos = Integer.parseInt(curPos.getText().toString());
        int tempSize = Integer.parseInt(totalSize.getText().toString());
        if (tempPos>1){
            --tempPos;
            curPos.setText(String.valueOf(tempPos));
            word.setText(vocabWords.get(tempPos).getName());
            meaning.setText(vocabWords.get(tempPos).getMeaning());
            sentence.setText(vocabWords.get(tempPos).getSentence());
        }
        else {
            prev.setClickable(false);
        }
    }
}
