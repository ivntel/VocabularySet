package com.example.geniusplaza.vocabularyset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DBRecyclerViewAdapter;
import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class WordsDatabase extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    RecyclerView dbRecyclerView;
    private LinearLayoutManager mLayoutManager;
    //the adapter that works with the array
    private DBRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        databaseHandler = new DatabaseHandler(this);


        dbRecyclerView = (RecyclerView) findViewById(R.id.wordDbRecyclerView);

        ArrayList<HashMap<String,Object>> wordList = databaseHandler.getWordInfo();

        dbRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dbRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DBRecyclerViewAdapter(this, wordList);
        dbRecyclerView.setAdapter(mAdapter);

        //wordImage.setImageBitmap(DBBitmapUtility.getImage((byte[]) wordList.get(0).get("image_data")));
    }
}