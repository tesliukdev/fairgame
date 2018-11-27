package com.tesliukdev.fairgame.gateway.cloud.rps

class RpsRequest {
    val jsonrpc = "2.0"
    val method = "generateIntegers"
    val params = Params()
    val id = 23751

    class Params {
        val apiKey = "00000000-0000-0000-0000-000000000000"
        val n = 1
        val min = 0
        val max = 2
        val base = 10
    }
}