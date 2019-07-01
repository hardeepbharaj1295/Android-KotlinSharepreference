package com.hardeep.sqlitepractice

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.hardeep.sharepreference.Users

class SqliteQueries {

    var context: Context? = null
    var sql: SqliteDatabase? = null

    constructor(context: Context)
    {
        this.context = context
        sql = SqliteDatabase(context)
    }

    fun addUser(users: Users): Int
    {
        val values = ContentValues()
        values.put(SqliteColumns.COLUMN_NAME,users.name)
        values.put(SqliteColumns.COLUMN_EMAIL,users.email)
        values.put(SqliteColumns.COLUMN_NUMBER,users.number)
        values.put(SqliteColumns.COUNTRY,users.country)
        values.put(SqliteColumns.GENDER,users.gender)
        values.put(SqliteColumns.PASSWORD,users.password)

        val db = sql!!.writableDatabase
        val result: Long = db.insert(SqliteColumns.TABLE_NAME,null,values)

        if (result>0)
        {
            return 1;
        }
        else{
            return 0;
        }
    }

    fun loginUser(email: String,password: String): Cursor
    {
        val db = sql!!.readableDatabase
        return db.query(SqliteColumns.TABLE_NAME,null,SqliteColumns.COLUMN_EMAIL+"=? AND "+SqliteColumns.PASSWORD+"=?",
            arrayOf(email,password),null,null,null)
    }

    fun fetchALl(): Cursor
    {
        val db = sql!!.readableDatabase
        return db.query(SqliteColumns.TABLE_NAME,null,null,null,null,null,null)
    }

    fun delete(id: String): Int
    {
        val db = sql!!.writableDatabase
        return db.delete(SqliteColumns.TABLE_NAME,SqliteColumns.COLUMN_ID+"=?", arrayOf(id))
    }

}