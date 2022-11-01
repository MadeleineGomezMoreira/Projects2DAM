package com.example.recyclerdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.recyclerdetail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            setSupportActionBar(bottomAppBar)
        }
        //setSupportActionBar(findViewById(R.id.bottomAppBar))


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        super.onCreateOptionsMenu(menu)
            menuInflater.inflate(R.menu.bottom_app_bar_menu, menu)
        return true;
    }

}