package com.hardeep.sqlitepractice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDatabase(context: Context?):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+ SqliteColumns.TABLE_NAME)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val create_user = "CREATE TABLE "+ SqliteColumns.TABLE_NAME + "("+ SqliteColumns.COLUMN_ID + " INTEGER PRIMARY KEY, " + SqliteColumns.COLUMN_NAME + " TEXT, " +
                SqliteColumns.COLUMN_EMAIL + " TEXT, " + SqliteColumns.COLUMN_NUMBER + " LONG, " + SqliteColumns.COUNTRY + " TEXT, " +
                SqliteColumns.PASSWORD + " TEXT, " + SqliteColumns.GENDER + " TEXT)";
        db!!.execSQL(create_user)

    }

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "sqlite.db"
    }
}