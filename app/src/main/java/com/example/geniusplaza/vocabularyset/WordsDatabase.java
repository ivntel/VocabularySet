package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DBRecyclerViewAdapter;
import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;

//This actuvity lets the user add the words clicked from camera to the vocab set database.
//it uses 'DatabaseHandler' adapter to handle it.
//It displays the recycler view to show all the clicks made so far by user.
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

        ArrayList<HashMap<String, Object>> wordList = databaseHandler.getWordInfo();
        dbRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dbRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DBRecyclerViewAdapter(this, wordList);
        dbRecyclerView.setAdapter(mAdapter);

        //wordImage.setImageBitmap(DBBitmapUtility.getImage((byte[]) wordList.get(0).get("image_data")));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem home){
        Intent i = new Intent(WordsDatabase.this, VocabDashboard.class);
        startActivity(i);
        return super.onOptionsItemSelected(home);
    }
}