package com.example.geniusplaza.vocabularyset;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.R.attr.animation;

public class ShowActivity extends AppCompatActivity {

    public TextView word, curPos, totalSize, meaning, sentence, resultTextView, hearWordText, userSpeech;
    public static String resId = null;
    public List<Word> vocabWords = new ArrayList<Word>();
    FloatingActionButton next, prev, hearWordButton;
    public CardView cardViewFlipped, cardView;
    public int flipCurrentCounter;
    public ConstraintLayout constraintLayout, flipBack;
    ProgressBar mProgressBar;
    FloatingActionButton speakButton;
    TextToSpeech t1;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Log.d("ShowActivity test:", resId);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_vocab_test);
        word = (TextView) findViewById(R.id.textViewWord);
        cardView = (CardView) findViewById(R.id.firstCard);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintFlip);
        curPos = (TextView) findViewById(R.id.textViewCurPos);
        totalSize = (TextView) findViewById(R.id.textViewSize);
        next = (FloatingActionButton) findViewById(R.id.floatingActionButtonNextWord);
        prev = (FloatingActionButton) findViewById(R.id.floatingActionButtonPreviousWord);
        speakButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonSpeak);
        resultTextView = (TextView) findViewById(R.id.textViewSTTResult);
        userSpeech = (TextView) findViewById(R.id.textViewUserSpeech);
        hearWordButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonHearPronounciation);
        hearWordText = (TextView) findViewById(R.id.textViewHearPronounciation);
        curPos.setText("1");
        mProgressBar.setVisibility(View.VISIBLE);

        //Api call to fetch words in a list to display them in flash cards.
        //words are linked to vocabset with resource id set in adapter.
        //This activity is associated with two xml 'activity_show' has the word. and 'flipped_layout' has meaning and sentence.
        RestClient.getExampleApi().flashcardCreate("Bearer " + ApiConstants.accessToken, resId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<WordsResource>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WordsResource value) {
                Toast.makeText(getApplicationContext(), "Get Words Successful!", Toast.LENGTH_SHORT).show();
                vocabWords = value.getWords();
                totalSize.setText(String.valueOf(vocabWords.size()));
                if (vocabWords.size() == 0) {
                    word.setText("YOU HAVE NO WORDS");
                    //logical changes to handle next and previous words not clicable once size limits have been reached.
                    next.setVisibility(View.GONE);
                    prev.setVisibility(View.GONE);
                } else {

                    word.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString()) - 1).getName());
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
        speakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                askSpeechInput();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem home){
        Intent i = new Intent(ShowActivity.this, VocabDashboard.class);
        startActivity(i);
        return super.onOptionsItemSelected(home);
    }
    public void cardViewClicked(View v) {
        //Animations handled here
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.swing_up_right);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintFlip);
        constraintLayout.startAnimation(animation);
        flipCurrentCounter = Integer.parseInt(curPos.getText().toString()) - 1;
        Toast.makeText(getApplicationContext(), "Card View Clicked", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.flipped_layout);

        meaning = (TextView) findViewById(R.id.displayMeaning);
        sentence = (TextView) findViewById(R.id.displaySentence);
        if (vocabWords.size() != 0) {
            meaning.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString()) - 1).getMeaning());
            sentence.setText(vocabWords.get(Integer.parseInt(curPos.getText().toString()) - 1).getSentence());
        }
        cardViewFlipped = (CardView) findViewById(R.id.flippedCard);
        //1st flip displays meaning again flip back should go back to the same state of word not to the 1st word.
        //handled that using a public static array vocabWords and curPos text view.
        cardViewFlipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Card View Clicked", Toast.LENGTH_SHORT).show();
                flipBack = (ConstraintLayout) findViewById(R.id.flipBackConstraintLayout);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.swing_up_left);
                flipBack.startAnimation(animation);

                setContentView(R.layout.activity_show);
                Log.d("Check for text", vocabWords.get(Integer.parseInt(curPos.getText().toString()) - 1).getName());
                word = (TextView) findViewById(R.id.textViewWord);
                curPos = (TextView) findViewById(R.id.textViewCurPos);
                speakButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonSpeak);
                resultTextView = (TextView) findViewById(R.id.textViewSTTResult);
                hearWordButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonHearPronounciation);
                hearWordText = (TextView) findViewById(R.id.textViewHearPronounciation);

                speakButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        askSpeechInput();
                    }
                });

                totalSize = (TextView) findViewById(R.id.textViewSize);
                word.setText(vocabWords.get(flipCurrentCounter).getName());
                curPos.setText(String.valueOf(flipCurrentCounter + 1));
                totalSize.setText(String.valueOf(vocabWords.size()));
            }
        });
    }

    public void nextVocabWord(View v) {
        next.setClickable(true);
        prev.setClickable(true);
        hearWordText.setVisibility(View.GONE);
        hearWordButton.setVisibility(View.GONE);
        resultTextView.setText("Result");
        //tempPos = 1
        int tempPos = Integer.parseInt(curPos.getText().toString());
        // tempSize = 12
        int tempSize = Integer.parseInt(totalSize.getText().toString());
        if (tempPos < tempSize) {

            word.setText(vocabWords.get(tempPos).getName());
            ++tempPos;
            curPos.setText(String.valueOf(tempPos));
        } else {
            next.setClickable(false);
        }
    }

    public void previousVocabWord(View v) {
        prev.setClickable(true);
        next.setClickable(true);
        hearWordText.setVisibility(View.GONE);
        hearWordButton.setVisibility(View.GONE);
        resultTextView.setText("Result");
        int tempPos = Integer.parseInt(curPos.getText().toString());
        int tempSize = Integer.parseInt(totalSize.getText().toString());
        if (tempPos > 1) {
            --tempPos;
            curPos.setText(String.valueOf(tempPos));
            word.setText(vocabWords.get(tempPos).getName());
        } else {
            prev.setClickable(false);
        }
    }

    //This method handles the Speech-To-Text
    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if (word.getText().toString().equalsIgnoreCase(result.get(0))) {
                        resultTextView.setText("CORRECT");
                        hearWordButton.setVisibility(View.INVISIBLE);
                        hearWordText.setVisibility(View.INVISIBLE);
                    } else {
                        resultTextView.setText("INCORRECT");
                        hearWordText.setVisibility(View.VISIBLE);
                        hearWordButton.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "What you said: " + "\"" + result.get(0) + "\"", Toast.LENGTH_LONG).show();

                        //this handles Text-To-Speech when a worng word is spoken to give right pronunciation
                        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                                if (status != TextToSpeech.ERROR) {
                                    t1.setLanguage(Locale.UK);

                                }
                            }
                        });
                        hearWordButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String toSpeak = word.getText().toString();
                                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }
                break;
            }
        }
    }
}
