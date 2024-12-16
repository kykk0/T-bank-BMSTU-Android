package com.example.hw1.di

import android.content.Context
import com.example.hw1.presentation.ui.addjoke.AddJokeFragment
import com.example.hw1.presentation.ui.jokedetails.JokeDetailsFragment
import com.example.hw1.presentation.ui.jokelist.JokeListFragment
import com.example.hw1.di.module.DataModule
import com.example.hw1.di.module.DomainModule
import com.example.hw1.di.module.PresentationModule
import com.example.hw1.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(addJokeFragment: AddJokeFragment)
    fun inject(jokeDetailsFragment: JokeDetailsFragment)
    fun inject(jokeListFragment: JokeListFragment)


    @Component.Factory
    interface AppComponentFactory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}