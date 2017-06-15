package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.POJO.CreateResource;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;

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
    EditText title,description;
    Spinner spinnerLanguage;
    public String text, tempLangText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vocab_set);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEditVS);
        title = (EditText)findViewById(R.id.editTextTitle);
        description = (EditText)findViewById(R.id.editTextDescription);


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
        if (extras.getInt("check") == 0 ){
            recyclerView.setVisibility(View.GONE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void saveButtonClicked(View v){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(title.getText().toString() == null || description.getText().toString() == null ){
            Toast.makeText(getApplicationContext(), "Please input the needed information", Toast.LENGTH_SHORT).show();
        }
        else {

            RestClient.getExampleApi().createVocabSet("Bearer " + pref.getString("access_token", ""), title.getText().toString(), description.getText().toString(), "1", "4").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<CreateResource>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(CreateResource value) {
                    Log.d("Successful response", "in add vocab set");
                    Toast.makeText(getApplicationContext(), "Save successful", Toast.LENGTH_SHORT).show();
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
}
