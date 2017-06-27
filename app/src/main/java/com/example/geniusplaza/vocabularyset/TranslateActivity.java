package com.example.geniusplaza.vocabularyset;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Locale;

/**
 * Created by geniusplaza on 6/19/17.
 */

public class TranslateActivity extends AppCompatActivity {

    String englishWord;
    TextView textViewSpanish, textViewEnglish;
    TextToSpeech t1, t2;
    public String spanishTranslation;
    Button bSpanish, bEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        englishWord = MainActivityGCV.imageArray.get(0);


        textViewSpanish = (TextView) findViewById(R.id.textViewSpanish);
        textViewEnglish = (TextView) findViewById(R.id.textViewEnglish);
        bSpanish = (Button) findViewById(R.id.buttonSpanish);
        bEnglish = (Button) findViewById(R.id.buttonEnglish);
        textViewEnglish.setText(englishWord);

        if (englishWord != null) {

            Toast.makeText(this, "Getting Translations",
                    Toast.LENGTH_LONG).show();

            new SaveTheFeed().execute();
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.UK);

                    }
                }
            });
            t2 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        //sets language to Spanish
                        Locale locSpanish = new Locale("spa", "MEX");
                        t2.setLanguage(locSpanish);
                    }
                }
            });
            bSpanish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toSpeak = textViewSpanish.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    t2.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
            bEnglish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toSpeak = textViewEnglish.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        } else {

            Toast.makeText(this, "Enter Words to Translate",
                    Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem home){
        Intent i = new Intent(TranslateActivity.this, VocabDashboard.class);
        startActivity(i);
        return super.onOptionsItemSelected(home);
    }
    class SaveTheFeed extends AsyncTask<Void, Void, Void> {

        String jsonString = "";
        String result = "";

        @Override
        protected Void doInBackground(Void... voids) {

            //EditText translateEditText = (EditText) findViewById(R.id.editText);

            // Get the text from EditText
            String wordsToTranslate = englishWord;

            wordsToTranslate = wordsToTranslate.replace(" ", "+");

            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

            HttpPost httpPost = new HttpPost("http://newjustin.com/translateit.php?action=translations&english_words=" + wordsToTranslate);
            Log.d("URL: ", httpPost.toString());

            httpPost.setHeader("Content-type", "application/json");

            InputStream inputStream = null;

            try {

                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity = response.getEntity();

                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");

                }

                jsonString = sb.toString();

                JSONObject jObject = new JSONObject(jsonString);

                //chooses the language at the 9th location in the list which is Spanish
                JSONArray jArray = jObject.getJSONArray("translations");
                JSONObject translationObject = jArray.getJSONObject(9);
                Log.d("holllla", translationObject.getString("spanish"));
                Log.d("jArray value: ", jArray.toString());
                Log.d("trryyyyyyyyyy", jArray.get(9).toString());
                spanishTranslation = translationObject.getString("spanish");
                //outputTranslations(jArray.get(9).toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textViewSpanish = (TextView) findViewById(R.id.textViewSpanish);
            textViewSpanish.setText(spanishTranslation);

        }
    }
}