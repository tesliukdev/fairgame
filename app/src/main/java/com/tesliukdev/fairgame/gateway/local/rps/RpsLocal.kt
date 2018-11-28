package com.tesliukdev.fairgame.gateway.local.rps

import com.tesliukdev.fairgame.gateway.Gateway
import io.reactivex.Single
import javax.inject.Inject

class RpsLocal
@Inject
constructor() : Gateway {

    companion object {
        const val gateWayName: String = "Local"
    }

    override fun getMove(): Single<String> {
        return Single.just((0..2).random().toString())
    }

    override fun sendMove(move: String) {
        //Game mode: with computer. No need to send move
    }
}