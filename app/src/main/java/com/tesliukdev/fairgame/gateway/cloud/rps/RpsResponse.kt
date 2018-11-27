package com.tesliukdev.fairgame.gateway.cloud.rps

class RpsResponse {
    var result: Result? = null
    val id = 23751

    class Result {
        var random: Random? = null
    }

    class Random {
        var data: List<Int>? = null
    }
}