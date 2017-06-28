package com.example.geniusplaza.vocabularyset;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.Locale;

public class PhraseActivity extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int REQ_CODE_SPEECH_INPUT_SPANISH = 200;
    FloatingActionButton englishSpeech, spanishSpeech;
    TextView spanishText, englishText, spanishTranslationTextView, englishTranslationTextView;
    public String tempEnglishText, tempSpanishText, spanishTranslation, englishTranslation;
    TextToSpeech t2;
    Button buttonHearSpanish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);
        englishSpeech = (FloatingActionButton) findViewById(R.id.floatingActionButtonSpeakEnglish);
        spanishSpeech = (FloatingActionButton) findViewById(R.id.floatingActionButtonSpeakSpanish);
        spanishText = (TextView) findViewById(R.id.textViewSpanishPhraseText);
        englishText = (TextView) findViewById(R.id.textViewEnglishPhraseText);
        spanishTranslationTextView = (TextView) findViewById(R.id.textViewSpanishTranslationFromEnglish);
        englishTranslationTextView = (TextView) findViewById(R.id.textViewEnglishTranslationFromSpanish);
        buttonHearSpanish = (Button) findViewById(R.id.buttonSpanishPhrase);

        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askSpeechInputEnglish();
            }
        });
        englishText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new SaveTheFeedEnglish().execute();
            }
        });
        spanishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askSpeechInputSpanish();
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
        buttonHearSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = spanishTranslationTextView.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t2.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    //This method handles the Speech-To-Text
    private void askSpeechInputEnglish() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi, speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }

    }

    //This method handles the Speech-To-Text in spansh
    private void askSpeechInputSpanish() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        Locale locSpanish = new Locale("spa", "MEX");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locSpanish);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hola, dice algo");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT_SPANISH);
        } catch (ActivityNotFoundException a) {

        }

    }
    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    englishText.setText(result.get(0).toString());
                    tempEnglishText = result.get(0).toString();
                }
                break;

            case REQ_CODE_SPEECH_INPUT_SPANISH:
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    spanishText.setText(result.get(0).toString());
                    tempSpanishText = result.get(0).toString();
                }
                break;
        }

    }
    class SaveTheFeedEnglish extends AsyncTask<Void, Void, Void> {

        String jsonString = "";
        String result = "";

        @Override
        protected Void doInBackground(Void... voids) {

            //EditText translateEditText = (EditText) findViewById(R.id.editText);

            // Get the text from EditText
            String wordsToTranslate = tempEnglishText;

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
            spanishTranslationTextView = (TextView) findViewById(R.id.textViewSpanishTranslationFromEnglish);
            spanishTranslationTextView.setText(spanishTranslation);
        }
    }
}
