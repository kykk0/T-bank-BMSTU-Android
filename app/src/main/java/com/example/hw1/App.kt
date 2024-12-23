package com.example.hw1

import android.app.Application
import com.example.hw1.data.datasource.local.JokeDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        JokeDatabase.initDatabase(this)
    }
}