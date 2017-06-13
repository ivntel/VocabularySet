package com.example.geniusplaza.vocabularyset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.geniusplaza.vocabularyset.POJO.AuthToken;
import com.example.geniusplaza.vocabularyset.Retrofit.GeniusApi;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    GeniusApi geniusApi;
    EditText uname, pword;
    String userName, password, secretKey;
    ApiConstants apiConstants;
    public AuthToken authToken;
    public  String refreshToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button)findViewById(R.id.loginbutton);
    }
}
