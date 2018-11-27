package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.gateway.cloud.rps.RpsCloud
import com.tesliukdev.fairgame.screens.rockpapscis.RPSViewModel
import com.tesliukdev.fairgame.screens.rockpapscis.model.RPSModel
import dagger.Component

@PerActivity
@Component(dependencies = [CloudComponent::class])
interface RpsComponent {
    val rpsModel: RPSModel
    val rpsCloud: RpsCloud
    val rpsViewModel: RPSViewModel
}