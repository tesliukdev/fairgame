package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.gameconnector.GameConnector
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsApi
import com.tesliukdev.fairgame.screens.setup.SelectGateWayViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CloudModule::class])
interface AppComponent {
    val gameConnector: GameConnector
    val rpsApi: RpsApi
    fun inject(viewModel: SelectGateWayViewModel)
}