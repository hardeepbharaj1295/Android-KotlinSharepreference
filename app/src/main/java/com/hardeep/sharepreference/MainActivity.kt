package com.hardeep.sharepreference

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.hardeep.sharepreference.preferences.SharedData
import com.hardeep.sqlitepractice.SqliteQueries

class MainActivity : AppCompatActivity() {

    private var editName: EditText? = null
    private var editPassword: EditText? = null

    var shareclas : SharedData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.email)
        editPassword = findViewById(R.id.password)

        shareclas = SharedData(this)
        if(!shareclas!!.getEmail().equals(""))
        {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }


    }

    fun submit(v: View) {
        val db = SqliteQueries(this)
        val cursor: Cursor = db.loginUser(editName!!.text.toString(),editPassword!!.text.toString())

        if (cursor.moveToFirst())
        {
            shareclas!!.setEmail(editName!!.text.toString())
            Toast.makeText(applicationContext,"Login Working",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        else
        {
            Toast.makeText(applicationContext,"Wrong Username/Password",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun register(v: View){
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
