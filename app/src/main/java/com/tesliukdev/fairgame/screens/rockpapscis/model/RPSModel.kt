package com.tesliukdev.fairgame.screens.rockpapscis.model

import android.arch.lifecycle.MutableLiveData
import com.tesliukdev.fairgame.utils.random
import io.reactivex.Observable

class RPSModel {

    var player1 = MutableLiveData<Player>()
    var player2 = MutableLiveData<Player>()

    var gameResult = MutableLiveData<String>()

    init {
        player1.value = Player("You")
        player2.value = Player("Computer")
    }

    fun getPlayer2Move(): Observable<Int> {
        return Observable.just((0..2).random())
    }

    fun fight(move1: Move, move2: Move) {
        when {
            move1 == move2 -> gameResult.value = "draw"
            move1.doIBeat(move2) -> {
                player1.value!!.score++
                gameResult.value = player1.value!!.name + " won"
                player1.postValue(player1.value)
            }
            else -> {
                player2.value!!.score++
                gameResult.value = player2.value!!.name + " won"
                player2.postValue(player2.value)
            }
        }
    }

    fun resetRound() {
        gameResult.value = null
    }

    fun resetGame() {
        gameResult.value = null
        player1.value!!.score = 0
        player1.value!!.score = 0
    }
}