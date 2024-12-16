package com.example.hw1

import android.app.Application
import com.example.hw1.data.datasource.local.JokeDatabase
import com.example.hw1.di.DaggerAppComponent
import com.example.hw1.di.AppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        JokeDatabase.initDatabase(this)
        appComponent = DaggerAppComponent.factory().create(this)
    }
}