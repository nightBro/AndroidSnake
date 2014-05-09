package com.asnake;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDatabase extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "TESTNAME";
    private static final String KEY_WORD = "KEYWORD";
    private static final String KEY_DEFINITION = "DEFINITION";

    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "HIGHSCORELIST";
    private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" + KEY_WORD + " TEXT, " +  KEY_DEFINITION + " TEXT);";
    
    TestDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    

    @Override
    public void onCreate(SQLiteDatabase arg0) {
    	arg0.execSQL(DICTIONARY_TABLE_CREATE);
//    	this.getReadableDatabase();
//    	this.getWritableDatabase();
    	
    }

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}