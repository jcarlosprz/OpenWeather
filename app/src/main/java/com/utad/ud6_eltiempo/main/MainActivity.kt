package com.utad.ud6_eltiempo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utad.ud6_eltiempo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()

    }
}