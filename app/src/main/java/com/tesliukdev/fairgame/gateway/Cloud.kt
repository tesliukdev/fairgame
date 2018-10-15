package com.tesliukdev.fairgame.gateway

class Cloud: Gateway {
    companion object {
        val gateWayName: String = Cloud::class.java.simpleName
    }

    override fun getMove() = "opponent's move!"

    override fun sendMove(move: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}