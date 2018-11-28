package com.tesliukdev.fairgame

import android.app.Application
import com.tesliukdev.fairgame.dagger.AppComponent
import com.tesliukdev.fairgame.dagger.DaggerAppComponent

class GameApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun appComponent(): AppComponent {
        return appComponent
    }
}