package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
        sEditText = (EditText) findViewById(R.id.searchEditText);
        vocabDashboardGridview = (GridView) findViewById(R.id.grid_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        vocabDashGridviewAdapter = new VocabDashGridviewAdapter(temp, this);
        vocabDashboardGridview.setAdapter(vocabDashGridviewAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resources = new Resources();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mProgressBar.setVisibility(View.VISIBLE);

        //MainActivity.getRefreshToken(ApiConstants.refreshToken);

        //Populating the dashboard with api call to load vocab resources made by user.
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", "", "True");
        RestClient.getExampleApi().postGetResources("Bearer " + ApiConstants.accessToken, resourceRequest)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Resources>() {
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.home:
                Intent i = new Intent(VocabDashboard.this, VocabDashboard.class);
                startActivity(i);
                break;
            case R.id.picture:
                //google cloud vision activity
                i = new Intent(this, ChooseActivity.class);
                startActivity(i);
                break;
            case R.id.phrase:
                i = new Intent(this, PhraseActivity.class);
                startActivity(i);
                break;
        }
//        Intent i = new Intent(VocabDashboard.this, VocabDashboard.class);
//        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    public void allVocabSetClicked(View v) {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        //MainActivity.getRefreshToken(ApiConstants.refreshToken);

        //This method can be use to populate the vocab dashboard with all the vocabulary resources
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", "", "False");
        RestClient.getExampleApi().postGetResources("Bearer " + ApiConstants.accessToken, resourceRequest)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Resources>() {
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

    public void searchButtonClicked(View v) {
        mProgressBar.setVisibility(View.VISIBLE);
        //MainActivity.getRefreshToken(ApiConstants.refreshToken);

        //Vocab dashboard populated with resources which are searched.
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", sEditText.getText().toString(), "False");
        RestClient.getExampleApi().postGetResources("Bearer " + ApiConstants.accessToken, resourceRequest)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Resources>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Resources value) {
                Log.d("Successful response", "in VocabDashboard");
                temp = value.getResources();
                resources = value;
                vocabDashGridviewAdapter.updateData(value);
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

    //Edit vocab set activity handles both creation of vocab set and editing it so the extra field "check" handles that
    public void addVocabSetClicked(View v) {
        Intent i = new Intent(this, EditVocabSet.class);
        i.putExtra("check", 0);
        startActivity(i);
    }

}
