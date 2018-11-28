package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.gateway.cloud.rps.RpsApi
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsCloud
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CloudModule::class])
interface CloudComponent {
    val rpsApi: RpsApi
    val rpsCloud: RpsCloud
}