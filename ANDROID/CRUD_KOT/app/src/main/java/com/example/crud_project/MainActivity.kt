package com.example.crud_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // el nombre del binding se hace usando el del archivo xml de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.viewButton.setOnClickListener {
            //si tuviesemos aquí el viewmodel pondríamos las funciones de dentro
            //para darle una función al botón
            //https://youtu.be/hhhSMXi0R3E ver ahora después en casa
        }
    }



}