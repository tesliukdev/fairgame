package com.tesliukdev.fairgame.screens

import android.support.v7.app.AppCompatActivity
import com.tesliukdev.fairgame.GameApplication
import com.tesliukdev.fairgame.dagger.AppComponent

open class BaseActivity: AppCompatActivity() {
    fun appComponent(): AppComponent {
        return (application as GameApplication).appComponent()
    }
}