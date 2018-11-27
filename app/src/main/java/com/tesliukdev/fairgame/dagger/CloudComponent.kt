package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.gateway.cloud.rps.RpsApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CloudModule::class])
interface CloudComponent {
    val rpsApi: RpsApi
}