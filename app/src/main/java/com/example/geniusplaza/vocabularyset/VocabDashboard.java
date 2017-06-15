package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geniusplaza.vocabularyset.POJO.Resource;
import com.example.geniusplaza.vocabularyset.POJO.ResourceNew;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.POJO.Resources;
import com.example.geniusplaza.vocabularyset.Retrofit.GeniusApi;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;
import com.example.geniusplaza.vocabularyset.Utils.VocabDashGridviewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabDashboard extends AppCompatActivity {

    List<ResourceNew> temp = new ArrayList<ResourceNew>();
    GridView vocabDashboardGridview;
    TextView userTextView, descriptionTextView, titleTextView;
    ImageView vocabSetImageView, userIconImageView;
    VocabDashGridviewAdapter vocabDashGridviewAdapter;
    Resources resources;
    SearchView mySearchView;
    EditText sEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = new Resources();
        setContentView(R.layout.activity_vocab_dashboard);
        sEditText = (EditText)findViewById(R.id.searchEditText);
        vocabDashboardGridview = (GridView) findViewById(R.id.grid_view);

        //vocabDashGridviewAdapter = new VocabDashGridviewAdapter(getApplicationContext(), resources);
        vocabDashGridviewAdapter = new VocabDashGridviewAdapter(temp,this);
        vocabDashboardGridview.setAdapter(vocabDashGridviewAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resources = new Resources();


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("access token", pref.getString("access_token", ""));
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", "");
        RestClient.getExampleApi().postGetResources("Bearer " + pref.getString("access_token", ""), resourceRequest).enqueue(getVocabResources);
    }
    public void searchButtonClicked(View v){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", sEditText.getText().toString());
        RestClient.getExampleApi().postGetResources("Bearer " + pref.getString("access_token", ""), resourceRequest).enqueue(getVocabResources);

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
    Callback<Resources> getVocabResources = new Callback<Resources>() {
        @Override
        public void onResponse(Call<Resources> call, Response<Resources> response) {
            //Log.d("response",Integer.toString(response.code()));

            if(response.isSuccessful()){
                Log.d("Successful response", "in VocabDashboard");
                temp = response.body().getResources();
                resources = response.body();
                vocabDashGridviewAdapter.updateData(response.body());
                Log.d("TRYY", temp.get(0).getDescription());
            }
            else{
                Log.d("Not successful response", "in VocabDashboard");
            }
        }

        @Override
        public void onFailure(Call<Resources> call, Throwable t) {
            Log.d("response",t.toString());
        }
    };


}
