package com.example.geniusplaza.vocabularyset;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DBRecyclerViewAdapter;
import com.example.geniusplaza.vocabularyset.POJO.AddWordBody;
import com.example.geniusplaza.vocabularyset.POJO.AddWordResponse;
import com.example.geniusplaza.vocabularyset.POJO.CreateResource;
import com.example.geniusplaza.vocabularyset.POJO.CreateVocabSetBody;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.POJO.WordsResource;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;
import com.example.geniusplaza.vocabularyset.Utils.AddWordAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditVocabSet extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText title,description,word,meaning,sentence;
    RecyclerView vocabularySetRecyclerView;
    LinearLayoutManager mLayoutManager;
    Spinner spinnerLanguage;
    public String text, tempLangText;
    public static String resourceId = null;
    AddWordAdapter mAdapter;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vocab_set);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEditVS);
        title = (EditText)findViewById(R.id.editTextTitle);
        description = (EditText)findViewById(R.id.editTextDescription);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_edit);


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        spinnerLanguage = (Spinner)findViewById(R.id.spinnerLanguage);
        List<String> lang = new ArrayList<String>();
        String[] langItems = new String []{"English", "Spanish"};
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, langItems);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(myadapter);
        text = spinnerLanguage.getSelectedItem().toString();

            if(text.equals("English")){
                tempLangText = "1";
            }
            else{
                tempLangText = "2";
            }


        Bundle extras = getIntent().getExtras();
        Log.d("Check intent value", (String.valueOf(extras.getInt("check")) ));
        if (extras.getInt("check") == 0 ){
            recyclerView.setVisibility(View.GONE);
        }
        else {
            mProgressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            Log.d("Edit0 Clicked", "Recycler view");
            Log.d("value of resource id", resourceId);
            //MainActivity.getRefreshToken(ApiConstants.refreshToken);
            Log.d("Accesstoken frm edit: ", ApiConstants.accessToken);
            RestClient.getExampleApi().flashcardCreate("Bearer " + ApiConstants.accessToken,resourceId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<WordsResource>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(WordsResource value) {
                    //AddWordAdapter addWordAdapter = new AddWordAdapter(getApplicationContext(),value.getWords());
                    Log.d("abcc", value.toString());
                    vocabularySetRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewEditVS);
                    vocabularySetRecyclerView.setHasFixedSize(false);
                    mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    vocabularySetRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new AddWordAdapter(getApplicationContext(), value.getWords());
                    vocabularySetRecyclerView.setAdapter(mAdapter);
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

    }

    public void saveButtonClicked(View v){
        if(title.getText().toString() == null || description.getText().toString() == null ){
            Toast.makeText(getApplicationContext(), "Please input the needed information", Toast.LENGTH_SHORT).show();
        }
        else {
            //MainActivity.getRefreshToken(ApiConstants.refreshToken);
            CreateVocabSetBody createVocabSetBody = new CreateVocabSetBody(title.getText().toString(), description.getText().toString(), "1", "4");
            RestClient.getExampleApi().createVocabSet("Bearer " + ApiConstants.accessToken, createVocabSetBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<CreateResource>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(CreateResource value) {
                    Log.d("Successful response", "in add vocab set");
                    Log.d("zzzzzzz", value.getError());
                    Toast.makeText(getApplicationContext(), "Save successful", Toast.LENGTH_SHORT).show();
                    if(DBRecyclerViewAdapter.createVocabSetCheck == 0){
                        ApiConstants.databaseResId = value.getResourceId().toString();

                        DBRecyclerViewAdapter.createVocabSetCheck = 1;
                        Intent i = new Intent(getApplicationContext(), WordsDatabase.class);
                        startActivity(i);
                    }
                    Intent i = new Intent(getApplicationContext(), VocabDashboard.class);
                    startActivity(i);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
            Intent i = new Intent(this, VocabDashboard.class);
            startActivity(i);
        }
    }

    public void cancelButtonClicked(View v){
        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, VocabDashboard.class);
        startActivity(i);
    }
    public void addNewWordButtonClicked(View v){
        //Toast.makeText(getApplicationContext(),"add new word clicked", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.row_recyclerview, null);
        dialogBuilder.setView(dialogView);
        final EditText wordName, wordMeaning, wordSentence, wordOrder;
        Spinner wordType;
        wordName = (EditText) dialogView.findViewById(R.id.textViewAddWord);
        wordMeaning = (EditText) dialogView.findViewById(R.id.textViewAddMeaning);
        wordSentence = (EditText) dialogView.findViewById(R.id.textViewAddSentence);
        wordOrder =(EditText) dialogView.findViewById(R.id.textViewOrder);
        wordType = (Spinner) dialogView.findViewById(R.id.spinnerWordType);
        List<String> lang = new ArrayList<String>();
        String[] langItems = new String []{"Noun", "Verb", "Adjective", "Adverb", "Conjuction", "Abbreviation", "Exclamation", "Preposition", "Pronoun", "Article", "Determiner"};
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_item, langItems);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wordType.setAdapter(myadapter);

        dialogBuilder.setTitle("Add new Word");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Log.d("Accesstoken 3: ", ApiConstants.accessToken);
                //MainActivity.getRefreshToken(ApiConstants.refreshToken);
                AddWordBody addWordBody = new AddWordBody(wordOrder.getText().toString(),wordName.getText().toString(),wordMeaning.getText().toString(), wordSentence.getText().toString(),"1");
                RestClient.getExampleApi().addVocabWords("Bearer " + ApiConstants.accessToken ,resourceId, addWordBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<AddWordResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddWordResponse value) {
                        Toast.makeText(getApplicationContext(),"Successfully saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}
