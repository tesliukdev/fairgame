package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.gateway.local.rps.RpsLocal
import dagger.Component

@Component
interface LocalComponent {
    val rpsLocal: RpsLocal
}