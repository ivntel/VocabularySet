package com.example.geniusplaza.vocabularyset;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.geniusplaza.vocabularyset.POJO.Resource;
import com.example.geniusplaza.vocabularyset.POJO.ResourceNew;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.POJO.Resources;
import com.example.geniusplaza.vocabularyset.Retrofit.GeniusApi;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabDashboard extends AppCompatActivity {

    Resources resources;
    List<ResourceNew> temp = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resources = new Resources();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("access token", pref.getString("access_token", ""));
        ResourceRequest resourceRequest = new ResourceRequest("1", "vocabularyset", "");
        RestClient.getExampleApi().postGetResources("Bearer " + pref.getString("access_token", ""), resourceRequest).enqueue(getVocabResources);

    }

    Callback<Resources> getVocabResources = new Callback<Resources>() {
        @Override
        public void onResponse(Call<Resources> call, Response<Resources> response) {
            //Log.d("response",Integer.toString(response.code()));

            if(response.isSuccessful()){
                Log.d("Successful response", "in VocabDashboard");
                temp = response.body().getResources();
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
