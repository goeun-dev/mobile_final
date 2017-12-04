package com.example.user.a2a_216230029_leegoeun;

/**
 * Created by user on 2017-12-03.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vocaDB.db";
    public static final String TABLE_VOCAS = "vocas";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_MEAN = "mean";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VOCAS_TABLE = "CREATE TABLE " +
                TABLE_VOCAS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_WORD
                + " TEXT," + COLUMN_MEAN + " TEXT" + ")";
        db.execSQL(CREATE_VOCAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOCAS);
        onCreate(db);
    }

    public void addVoca(Voca voca) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD, voca.getWord());
        values.put(COLUMN_MEAN, voca.getMean());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_VOCAS, null, values);
        db.close();
    }

    public Voca findVoca(String word) {
        String query = "SELECT * FROM " + TABLE_VOCAS + " WHERE " +
                COLUMN_WORD + " = \"" + word + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Voca voca = new Voca();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            voca.setID(Integer.parseInt(cursor.getString(0)));
            voca.setWord(cursor.getString(1));
            voca.setMean(cursor.getString(2));
            cursor.close();
        } else {
            voca = null;
        }
        db.close();
        return voca;
    }

    public boolean deleteVoca(String word) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_VOCAS + " WHERE " +
                COLUMN_WORD + " = \"" + word + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Voca voca = new Voca();

        if (cursor.moveToFirst()) {
            voca.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_VOCAS, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(voca.getID())});
            cursor.close();
            result = true;
        }
        db.close();

        return result;
    }
}
