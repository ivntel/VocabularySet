package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.geniusplaza.vocabularyset.POJO.AuthToken;
import com.example.geniusplaza.vocabularyset.Retrofit.GeniusApi;
import com.example.geniusplaza.vocabularyset.Retrofit.RestClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GeniusApi geniusApi;
    EditText uname, pword;
    String userName, password, secretKey;
    ApiConstants apiConstants;
    public AuthToken authToken;
    public  String accessToken;
    Button loginButton;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.loginbutton);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_login);
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
            mProgressBar.setVisibility(View.VISIBLE);
            secretKey = apiConstants.getBase64();
            RestClient.getExampleApi().postCredentials("Basic "+secretKey, userName, password, "password").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<AuthToken>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AuthToken value) {
                    Log.d("in main activity","SUCCCESSSS");
                    accessToken = value.getAccessToken();
                    ApiConstants.accessToken = accessToken;
                    mProgressBar.setVisibility(View.GONE);
                    Intent i = new Intent(getApplicationContext(), VocabDashboard.class);
                    startActivity(i);

                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(MainActivity.this, "1st token request fail", Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
    public static void getRefreshToken(final String refreshToken) {
        ApiConstants apiConstants = new ApiConstants();
        RestClient.getExampleApi().postRefreshToken("Basic " + apiConstants.getBase64(), refreshToken, "refresh_token").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<AuthToken>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AuthToken value) {
                ApiConstants.accessToken = value.getAccessToken();
                ApiConstants.refreshToken = value.getRefreshToken();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("Inside delay",refreshToken);
                RestClient.getExampleApi().postRefreshToken("Basic " + secretKey, refreshToken,"refresh_token").enqueue(refreshCallback);
            }
        },3000);
    }*/
    }
}
