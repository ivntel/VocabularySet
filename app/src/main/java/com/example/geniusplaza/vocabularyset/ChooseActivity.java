package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {

    public static String optSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem home){
        Intent i = new Intent(ChooseActivity.this, VocabDashboard.class);
        startActivity(i);
        return super.onOptionsItemSelected(home);
    }
    public void objectButtonClicked(View v) {
        Intent i = new Intent(this, MainActivityGCV.class);
        optSelected = "o";
        startActivity(i);
    }

    public void textButtonClicked(View v) {
        Intent i = new Intent(this, MainActivityGCV.class);
        optSelected = "t";
        startActivity(i);
    }

    public void landmarkButtonClicked(View v) {
        Intent i = new Intent(this, MainActivityGCV.class);
        optSelected = "l";
        startActivity(i);
    }

    public void goToWordsDBbutton(View view) {
        Intent i = new Intent(this, WordsDatabase.class);
        startActivity(i);
    }
}