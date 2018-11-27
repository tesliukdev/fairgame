package com.tesliukdev.fairgame.screens.rockpapscis

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.tesliukdev.fairgame.screens.rockpapscis.model.Move
import com.tesliukdev.fairgame.screens.rockpapscis.model.RPSModel
import javax.inject.Inject

class RPSViewModel
@Inject
constructor(private var rpsModel: RPSModel): ViewModel() {
    var player1Score = ObservableField<String>("0")
    var player2Score = ObservableField<String>("0")
    var player1Move = ObservableField<Move?>()
    var player2Move = ObservableField<Move?>()
    var gameResult = ObservableField<String>()
    var errorMessage = ObservableField<String>()

    init {
        rpsModel.player1.observeForever{ t ->  player1Score.set(t!!.score.toString()) }
        rpsModel.player2.observeForever{ t ->  player2Score.set(t!!.score.toString()) }
        rpsModel.gameResult.observeForever{ t ->  gameResult.set(t) }
    }

    fun onClickMoveButton(move: Move) {
        resetRound()
        player1Move.set(move)
        rpsModel.getPlayer2Move(this::onMoveReceived, this::onError)
    }

    private fun onMoveReceived(move: Int) {
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
        player1Move.set(null)
        player2Move.set(null)
        rpsModel.resetRound()
    }

    fun resetGame() {
        resetRound()
        rpsModel.resetGame()
    }
}