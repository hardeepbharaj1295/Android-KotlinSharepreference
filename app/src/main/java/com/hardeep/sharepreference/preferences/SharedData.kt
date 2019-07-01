package com.hardeep.sharepreference.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedData {
    var shared: SharedPreferences? = null

    constructor(context: Context)
    {
        shared = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
    }

    fun setEmail(email: String)
    {
        val editor = shared!!.edit()
        editor.putString("session",email)
        editor.apply()
    }

    fun getEmail():String
    {
        return shared!!.getString("session","")
    }
}