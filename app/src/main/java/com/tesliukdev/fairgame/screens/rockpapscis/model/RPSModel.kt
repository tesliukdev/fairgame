package com.tesliukdev.fairgame.screens.rockpapscis.model

import android.arch.lifecycle.MutableLiveData

class RPSModel {

    var player1 = Player("You")
    var player2 = Player("Computer")

    var winner = MutableLiveData<Player>()

    fun fight(move1: Move, move2: Move) {
        when {
            move1 == move2 -> winner.value = null
            move1.doIBeat(move2) -> {
                player1.score++
                winner.value = player1
            }
            else -> {
                player2.score++
                winner.value = player2
            }
        }
    }
}