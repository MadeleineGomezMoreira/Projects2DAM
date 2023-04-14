package com.example.recyclerdetail.ui.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.recyclerdetail.R
import com.example.recyclerdetail.databinding.ActivityMainBinding
import com.example.recyclerdetail.ui.screens.BottomAppBarFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<BottomAppBarFragment>(R.id.fragment_container_view)
            }
            val view = root
            setContentView(view)
        }
    }
}