package com.example.hw1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1.R
import com.example.hw1.di.AppComponent
import com.example.hw1.di.DaggerAppComponent

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.factory().create(this)
        setContentView(R.layout.activity_main)
    }
}
