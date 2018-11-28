package com.tesliukdev.fairgame.gameconnector

import com.tesliukdev.fairgame.gateway.Gateway
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameConnector
@Inject
constructor() {
    lateinit var gateway: Gateway

}