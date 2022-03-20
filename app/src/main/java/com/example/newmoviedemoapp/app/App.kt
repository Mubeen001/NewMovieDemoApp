package com.example.myapplication.app

import android.app.Application
import com.example.newmoviedemoapp.diModules.apModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App :Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(apModule().getModule(this@App))
        }
    }

}