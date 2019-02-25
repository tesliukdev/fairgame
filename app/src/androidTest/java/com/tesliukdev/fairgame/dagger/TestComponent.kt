package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.RPSTest
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CloudModule::class])
interface TestComponent : AppComponent {
    fun inject(test: RPSTest)
}