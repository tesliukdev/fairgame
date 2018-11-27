package com.tesliukdev.fairgame.gateway

import io.reactivex.Single

interface Gateway {
    fun getMove() : Single<String>
    fun sendMove(move : String)
}