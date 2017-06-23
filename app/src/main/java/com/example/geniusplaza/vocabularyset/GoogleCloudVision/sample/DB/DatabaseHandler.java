package com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.DB;

/**
 * Created by geniusplaza on 6/23/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by geniusplaza on 6/21/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    static final String DATABASE_NAME = "wordDB";
    static final int DATABASE_VERSION = 1;
    static final String WORD_TABLE_NAME = "wordsTable";

    static final String KEY_ID = "id";
    static final String WORD_NAME = "word";
    static final String WORD_MEANING = "meaning";
    static final String WORD_SENTENCE = "sentence";
    static final String WORD_IMAGE = "image_data";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATABASE", "Inside on create method of DatabaseHandler");

        String CREATE_WORD_INFO_TABLE = "CREATE TABLE " + WORD_TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + WORD_NAME + " TEXT,"
                + WORD_MEANING + " TEXT,"
                + WORD_SENTENCE + " TEXT,"
                + WORD_IMAGE + " BLOB" + ")";


        db.execSQL(CREATE_WORD_INFO_TABLE);
        Log.d("DATABASE", "Successfully created table location info");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + WORD_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public int addWordInfo(String wordName, String wordDefinition, String wordSentence, byte[] imageWord) {
        /*First check if same latitude, longitude combination already exists*/
        ArrayList<HashMap<String,Object>> wordList = getWordInfo();

        for(HashMap<String, Object> wordInfo : wordList){
            String Word = (String) wordInfo.get(WORD_NAME);


            Log.d("DB", "Checking db value of word : " + Word + " with new value : " + wordDefinition);


            if((Word.equals(wordName))){
                return 1;
            }
        }
        //if no duplicates found added to DB here
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("DATABASE", "Called getWritableDatabase in addLocation Data Method ");

        ContentValues values = new ContentValues();

        values.put(WORD_NAME, wordName);
        values.put(WORD_MEANING, wordDefinition);
        values.put(WORD_SENTENCE, wordSentence);
        values.put(WORD_IMAGE, imageWord);

        // Inserting Row
        db.insert(WORD_TABLE_NAME, null, values);
        db.close(); // Closing database connection

        return 2;
    }

    //Grabs all components of DB so that they can be displayed
    public ArrayList<HashMap<String,Object>> getWordInfo() {
        ArrayList<HashMap<String,Object>> wordList = new ArrayList<HashMap<String,Object>>();
        String selectQuery = "SELECT * FROM " + WORD_TABLE_NAME + " ORDER BY ROWID";

        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DATABASE", "Called getReadableDatabase in getLocationInfo method");
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {
                HashMap<String, Object> wordInfo = new HashMap<String, Object>();
                wordInfo.put(WORD_NAME, cursor.getString(cursor.getColumnIndex(WORD_NAME)));
                wordInfo.put(WORD_MEANING, cursor.getString(cursor.getColumnIndex(WORD_MEANING)));
                wordInfo.put(WORD_SENTENCE, cursor.getString(cursor.getColumnIndex(WORD_SENTENCE)));
                wordInfo.put(WORD_IMAGE, cursor.getBlob(cursor.getColumnIndex(WORD_IMAGE)));
                wordList.add(wordInfo);
            }
        } finally{
            cursor.close();
            cursor.close();
            db.close();
        }
        // return location info
        return wordList;
    }

    public boolean deleteWord(String wordName){
        SQLiteDatabase data = this.getWritableDatabase();
        String table = WORD_TABLE_NAME;
        String whereClause = WORD_NAME + "=?";
        String whereArgs[] = new String[] {String.valueOf(wordName)};

        int count = data.delete(table, whereClause, whereArgs);
        data.close();

        if(count == 1){
            return true;
        }

        return false;
    }

    public void resetWordEntry() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(WORD_TABLE_NAME, "", null);
        db.close();
    }
}
