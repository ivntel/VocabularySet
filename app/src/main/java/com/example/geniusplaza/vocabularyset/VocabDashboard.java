package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.geniusplaza.vocabularyset.POJO.ResourceNew;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.POJO.Resources;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;
import com.example.geniusplaza.vocabularyset.Utils.VocabDashGridviewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VocabDashboard extends AppCompatActivity {

    public List<ResourceNew> temp = new ArrayList<ResourceNew>();
    GridView vocabDashboardGridview;
    TextView userTextView, descriptionTextView, titleTextView;
    ImageView vocabSetImageView, userIconImageView;
    public VocabDashGridviewAdapter vocabDashGridviewAdapter;
    Resources resources;
    SearchView mySearchView;
    EditText sEditText;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = new Resources();
        setContentView(R.layout.activity_vocab_dashboard);
        sEditText = (EditText)findViewById(R.id.searchEditText);
        vocabDashboardGridview = (GridView) findViewById(R.id.grid_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        vocabDashGridviewAdapter = new VocabDashGridviewAdapter(temp,this);
        vocabDashboardGridview.setAdapter(vocabDashGridviewAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resources = new Resources();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("access token", pref.getString("access_token", ""));
        mProgressBar.setVisibility(View.VISIBLE);

        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", "", "True");
        RestClient.getExampleApi().postGetResources("Bearer " + pref.getString("access_token", ""), resourceRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Resources>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Resources value) {
                Log.d("Successful response", "in VocabDashboard");
                temp = value.getResources();
                resources = value;
                vocabDashGridviewAdapter.updateData(value);
                Log.d("TRYY", temp.get(0).getDescription());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Not successful response", "in VocabDashboard");
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void allVocabSetClicked(View v){
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("access token", pref.getString("access_token", ""));
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", "", "False");
        RestClient.getExampleApi().postGetResources("Bearer " + pref.getString("access_token", ""), resourceRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Resources>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Resources value) {
                Log.d("Successful response", "in VocabDashboard");
                mProgressBar.setVisibility(View.GONE);
                temp = value.getResources();
                resources = value;
                vocabDashGridviewAdapter.updateData(value);
                Log.d("TRYY", temp.get(0).getDescription());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Not successful response", "in VocabDashboard");
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void searchButtonClicked(View v){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mProgressBar.setVisibility(View.VISIBLE);
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", sEditText.getText().toString(),"False");
        RestClient.getExampleApi().postGetResources("Bearer " + pref.getString("access_token", ""), resourceRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Resources>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Resources value) {
                Log.d("Successful response", "in VocabDashboard");
                temp = value.getResources();
                resources = value;
                vocabDashGridviewAdapter.updateData(value);
                Log.d("TRYY", temp.get(0).getDescription());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Not successful response", "in VocabDashboard");
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {

            }
        });

    }
    public void addVocabSetClicked(View v){
        Intent i = new Intent(this, EditVocabSet.class);
        i.putExtra("check", 0);
        startActivity(i);
    }

    public void editVocabSetClicked(View v){
        Intent i = new Intent(this, EditVocabSet.class);
        i.putExtra("check", 1);
        startActivity(i);
    }


}
