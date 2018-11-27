package com.tesliukdev.fairgame.screens.rockpapscis.model

import android.arch.lifecycle.MutableLiveData
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsCloud
import javax.inject.Inject

class RPSModel
@Inject
constructor(private val rpsCloud: RpsCloud) {

    var player1 = MutableLiveData<Player>()
    var player2 = MutableLiveData<Player>()

    var gameResult = MutableLiveData<String>()

    init {
        player1.value = Player("You")
        player2.value = Player("Computer")
    }

    //    fun getPlayer2Move(onMoveReceived: Consumer<in String>, onError: Consumer<in Throwable>) {
    fun getPlayer2Move(onMoveReceived: ((Int) -> Unit), onError: ((Throwable) -> Unit)) {
        rpsCloud.getMove()
                .map { t: String -> t.toInt() }
                .subscribe(onMoveReceived, onError)
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