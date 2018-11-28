package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.screens.rockpapscis.RPSViewModel
import com.tesliukdev.fairgame.screens.rockpapscis.model.RPSModel
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class])
interface RpsComponent {
    val rpsModel: RPSModel
    val rpsViewModel: RPSViewModel
}