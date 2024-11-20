package com.example.onlinecourses

import android.app.Application
import com.example.onlinecourses.di.dataModule
import com.example.onlinecourses.di.interactorModule
import com.example.onlinecourses.di.repositoryModule
import com.example.onlinecourses.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewmodelModule)
        }
    }
}