package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie = findViewById<MovieView>(R.id.movie)
        movie.setMovie(Movie("Batman","https://loremflickr.com/320/240/dog"))

    }

}