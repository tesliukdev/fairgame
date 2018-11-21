package com.tesliukdev.fairgame.screens.rockpapscis

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.tesliukdev.fairgame.screens.rockpapscis.model.Move
import com.tesliukdev.fairgame.screens.rockpapscis.model.RPSModel

class RPSViewModel(private var rpsModel: RPSModel): ViewModel() {
    private var player1Score = ObservableInt(0)
    private var player2Score = ObservableInt(0)
    private var player1Move = ObservableField<Move>()
    private var player2Move = ObservableField<Move>()
    private var gameResult = ObservableField<String>()
    private var errorMessage = ObservableField<String>()

    init {
        rpsModel.player1.observeForever{ t ->  player1Score.set(t!!.score) }
        rpsModel.player2.observeForever{ t ->  player2Score.set(t!!.score) }
        rpsModel.gameResult.observeForever{ t ->  gameResult.set(t) }
    }

    fun onClickMoveButton(move: Move) {
        resetRound()
        player1Move.set(move)
        rpsModel.getPlayer2Move()
                .subscribe(this::onMoveReceived, this::onError)
    }

    fun onMoveReceived(move: Int) {
        when(move) {
            0 -> player2Move.set(Move.SCISSORS)
            1 -> player2Move.set(Move.ROCK)
            2 -> player2Move.set(Move.PAPER)
        }
        rpsModel.fight(player1Move.get()!!, player2Move.get()!!)
    }

    private fun onError(throwable: Throwable) {
        errorMessage.set(throwable.message)
    }

    private fun resetRound() {
        player1Move = ObservableField()
        player2Move = ObservableField()
        rpsModel.resetRound()
    }

    fun resetGame() {
        resetRound()
        rpsModel.resetGame()
    }
}