package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.room.DataBase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    var room:DataBase? = null

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(modKoin)
        }
    }

}