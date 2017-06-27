package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DBBitmapUtility;
import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DatabaseHandler;
import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.POJO.Result;
import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.POJO.WordContent;
import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.Retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DictionaryActivity extends AppCompatActivity {

    TextView meaning, sentence;
    List<Result> definitionArray, exampleArray;
    List<String> definition, example;
    int counter = 0;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        meaning = (TextView) findViewById(R.id.textViewDefinition);
        sentence = (TextView) findViewById(R.id.textViewExample);
        definition = new ArrayList<String>();
        example = new ArrayList<String>();
        exampleArray = new ArrayList<Result>();
        definitionArray = new ArrayList<Result>();
        databaseHandler = new DatabaseHandler(this);
        Log.d("Word looking up", MainActivityGCV.imageArray.get(0));

        RestClient.getExampleApi().wordLookup(MainActivityGCV.imageArray.get(0)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<WordContent>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("inside thw call", " success");
            }

            @Override
            public void onNext(WordContent value) {
                Log.d("checking the api", value.toString());
                definitionArray = value.getResults();
                counter = 0;

                if (definitionArray.get(0).getDefinition() == null) {
                    meaning.setText("No Definition Found");
                } else {
                    meaning.setText(definitionArray.get(0).getDefinition());
                }
                if (definitionArray.get(0).getExamples() == null) {
                    sentence.setText("No Example Found");
                } else {
                    sentence.setText(definitionArray.get(0).getExamples().toString());
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addToDatabaseButtonClicked(View v) {
        byte[] holderString = DBBitmapUtility.getBytes(MainActivityGCV.bitmap);
        databaseHandler.addWordInfo(MainActivityGCV.imageArray.get(0), meaning.getText().toString(), sentence.getText().toString(), holderString);
        Toast.makeText(getApplicationContext(), "Add to database Successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, WordsDatabase.class);
        startActivity(i);

    }
}
