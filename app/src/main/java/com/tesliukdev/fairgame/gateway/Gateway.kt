package com.tesliukdev.fairgame.gateway

interface Gateway {
    fun getMove() : String
    fun sendMove(move : String)
}