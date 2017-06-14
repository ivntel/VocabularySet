package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.POJO.AuthToken;
import com.example.geniusplaza.vocabularyset.Retrofit.GeniusApi;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    GeniusApi geniusApi;
    EditText uname, pword;
    String userName, password, secretKey;
    ApiConstants apiConstants;
    public AuthToken authToken;
    public  String accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiConstants = new ApiConstants();
        authToken = new AuthToken();

    }
    public void loginButtonClicked(View v){
        uname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        userName = uname.getText().toString();
        password = pword.getText().toString();
        Log.d("aaaaaaaa", userName);
        Log.d("aaaaaaaa", password);
        if (userName == null || password == null){
            Toast.makeText(this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("Main Activity","API call");
            secretKey = apiConstants.getBase64();
            RestClient.getExampleApi().postCredentials("Basic "+secretKey, userName, password, "password").enqueue(tokenCallback);
        }
    }
    Callback<AuthToken> tokenCallback = new Callback<AuthToken>() {
        @Override
        public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
            if(response.isSuccessful()){
                Log.d("in main activity","SUCCCESSSS");
                accessToken = response.body().getAccessToken();
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("access_token",accessToken);
                editor.apply();
                String temp = pref.getString("access_token","");
                Log.d("aaaaaaaaaaa",temp);
                Intent i = new Intent(getApplicationContext(), VocabDashboard.class);
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this, "1st token request fail", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onFailure(Call<AuthToken> call, Throwable t) {

        }
    };
}
