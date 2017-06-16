package com.example.geniusplaza.vocabularyset;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
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

import static android.R.attr.animation;

public class ShowActivity extends AppCompatActivity {

    public TextView word, curPos,totalSize, meaning, sentence;
    public static String resId = null;
    public List<Word> vocabWords = new ArrayList<Word>();
    FloatingActionButton next, prev;
    public CardView cardViewFlipped, cardView;
    public int flipCurrentCounter;
    public ConstraintLayout constraintLayout, flipBack;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Log.d("ShowActivity test:", resId);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("access token", pref.getString("access_token", ""));

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_vocab_test);
        word = (TextView) findViewById(R.id.textViewWord);
        cardView = (CardView) findViewById(R.id.firstCard);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintFlip);
        curPos = (TextView)findViewById(R.id.textViewCurPos);
        totalSize = (TextView)findViewById(R.id.textViewSize);
        next = (FloatingActionButton)findViewById(R.id.floatingActionButtonNextWord);
        prev = (FloatingActionButton)findViewById(R.id.floatingActionButtonPreviousWord);
        curPos.setText("1");
        mProgressBar.setVisibility(View.VISIBLE);

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
                    next.setVisibility(View.GONE);
                    prev.setVisibility(View.GONE);
                }
                else {

                    word.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString())-1).getName());
                }
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void cardViewClicked(View v){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.swing_up_right);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintFlip);
        constraintLayout.startAnimation(animation);
        flipCurrentCounter = Integer.parseInt(curPos.getText().toString()) - 1;
        Toast.makeText(getApplicationContext(),"Card View Clicked",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.flipped_layout);

        meaning = (TextView) findViewById(R.id.displayMeaning);
        sentence = (TextView) findViewById(R.id.displaySentence);
        if(vocabWords.size() != 0) {
            meaning.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString()) - 1).getMeaning());
            sentence.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString()) - 1).getSentence());
        }
        cardViewFlipped = (CardView)findViewById(R.id.flippedCard);
        cardViewFlipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Card View Clicked",Toast.LENGTH_SHORT).show();
                flipBack = (ConstraintLayout) findViewById(R.id.flipBackConstraintLayout);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.swing_up_left);
                flipBack.startAnimation(animation);

                setContentView(R.layout.activity_show);
                Log.d("Check for text", vocabWords.get(Integer.parseInt(curPos.getText().toString())-1).getName());
                word = (TextView) findViewById(R.id.textViewWord);
                curPos = (TextView)findViewById(R.id.textViewCurPos);

                totalSize = (TextView)findViewById(R.id.textViewSize);
                word.setText(vocabWords.get(flipCurrentCounter).getName());
                curPos.setText(String.valueOf(flipCurrentCounter+1));
                totalSize.setText(String.valueOf(vocabWords.size())) ;
            }
        });
    }
    public void nextVocabWord(View v){
        next.setClickable(true);
        prev.setClickable(true);
        //tempPos = 1
        int tempPos = Integer.parseInt(curPos.getText().toString());
        // tempSize = 12
        int tempSize = Integer.parseInt(totalSize.getText().toString());
        if (tempPos<tempSize){

            word.setText(vocabWords.get(tempPos).getName());
            ++tempPos;
            curPos.setText(String.valueOf(tempPos));
        }
        else{
            next.setClickable(false);
        }
    }
    public  void previousVocabWord(View v){
        prev.setClickable(true);
        next.setClickable(true);
        int tempPos = Integer.parseInt(curPos.getText().toString());
        int tempSize = Integer.parseInt(totalSize.getText().toString());
        if (tempPos>1){
            --tempPos;
            curPos.setText(String.valueOf(tempPos));
            word.setText(vocabWords.get(tempPos).getName());
        }
        else {
            prev.setClickable(false);
        }
    }
}
