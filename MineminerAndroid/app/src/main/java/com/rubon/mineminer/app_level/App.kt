package com.rubon.mineminer.app_level

import android.app.Application
import com.rubon.mineminer.app_level.di.AppComponent
import com.rubon.mineminer.app_level.di.DaggerAppComponent
import com.rubon.mineminer.app_level.di.module.DBModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .dBModule(DBModule(applicationContext))
                .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}