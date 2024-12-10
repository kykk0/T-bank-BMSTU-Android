package com.example.hw1.ui.main

import android.app.Application
import com.example.hw1.data.db.JokeDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        JokeDatabase.initDatabase(this)
    }
}