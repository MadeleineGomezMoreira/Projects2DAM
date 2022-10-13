package com.example.examplebuttontext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val sendButton = findViewById<Button>(R.id.sendButton)

        val adminMail = getString(R.string.adminMail)
        val adminPassword = getString(R.string.adminPassword)

        sendButton.setOnClickListener{

            if (email.text.toString() == adminMail && password.text.toString() == adminPassword){
                    Toast.makeText(this, getString(R.string.successfulLogin), Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, getString(R.string.FailedLogin), Toast.LENGTH_SHORT).show()
                }

        }
    }



}