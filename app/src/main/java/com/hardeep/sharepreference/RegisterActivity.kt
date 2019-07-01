package com.hardeep.sharepreference

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.hardeep.sqlitepractice.SqliteQueries

class RegisterActivity : AppCompatActivity() {

    private var nameId: EditText? = null
    private var emailId: EditText? = null
    private var numberId: EditText? = null
    private var spinnerId: Spinner? = null
    private var passwordId: EditText? = null
    private var radioGroupId: RadioGroup? = null
    private var radioButtonId: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        nameId = findViewById(R.id.name)
        emailId = findViewById(R.id.email)
        numberId = findViewById(R.id.number)
        spinnerId = findViewById(R.id.spinner)
        passwordId = findViewById(R.id.password)
        radioGroupId = findViewById(R.id.radioGroup)

        ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_list_item_1)
            .also {
                    arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                spinnerId!!.adapter = arrayAdapter
            }

        radioGroupId!!.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener {
                group, checkedId -> radioButtonId = findViewById(checkedId)
            Toast.makeText(this," On checked change : ${radioButtonId!!.text}", Toast.LENGTH_LONG).show()
        }
        )
    }

    fun register(v: View)
    {
        val id: Int = radioGroupId!!.checkedRadioButtonId
        if (id!=-1){
            radioButtonId = findViewById(id)



            Toast.makeText(applicationContext,"On button click : ${radioButtonId!!.text}",
                Toast.LENGTH_SHORT).show()

            val db = SqliteQueries(this)
            val users = Users(nameId!!.text.toString(),emailId!!.text.toString(),numberId!!.text.toString().toLong(),spinnerId!!.selectedItem.toString(),passwordId!!.text.toString(),radioButtonId!!.text.toString())
            val res: Int = db.addUser(users)
            if (res>0)
            {
                AlertDialog.Builder(this)
                    .setTitle("Message!")
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setMessage("Register Successfully")
                    .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    })
                    .show()
            }
            else{
                AlertDialog.Builder(this)
                    .setTitle("Alert!")
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setMessage("Please Try Again")
                    .setPositiveButton("Ok", null)
                    .show()
            }
        }else{
            // If no radio button checked in this radio group
            Toast.makeText(applicationContext,"On button click : nothing selected",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun login(v: View)
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
