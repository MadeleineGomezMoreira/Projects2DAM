package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.emailTxtField)
        val phoneNum = findViewById<EditText>(R.id.phoneTxtField)
        val sendButton = findViewById<Button>(R.id.sendBtn)

        sendButton.setOnClickListener{
            // Log.d("MainActivity","Button Clicked")
            //val message = email.text
            val message = "Email: ${email.text}, Teléfono: ${phoneNum.text}"
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }

    }

    //Las activities extienden de un contexto

    //Los string templates te permiten meter variables dentro de una cadena de texto


    // Views y Viewgroups
    /*
    Es lo que compone el layout de la aplicacion.


    VIEWS - vistas unicas, representa un componente dentro de la pantalla.
    VIEWGROUP - conjunto de vistas.

    La mayoría de views extienden de una principal más básica

     */

}