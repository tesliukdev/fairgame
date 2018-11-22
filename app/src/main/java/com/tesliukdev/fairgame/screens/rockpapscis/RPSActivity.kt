package com.tesliukdev.fairgame.screens.rockpapscis

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.tesliukdev.fairgame.R
import com.tesliukdev.fairgame.databinding.ActivityRpsBinding
import com.tesliukdev.fairgame.screens.rockpapscis.model.Move
import com.tesliukdev.fairgame.screens.rockpapscis.model.RPSModel


class RPSActivity : AppCompatActivity() {

    companion object {
        fun getInstance(context: Context): Intent = Intent(context, RPSActivity::class.java)
    }

    private val model = RPSModel()
    private val viewModel = RPSViewModel(model)
    private lateinit var binding: ActivityRpsBinding

    private val onPlayer1MoveCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) {
            when (viewModel.player1Move.get()) {
                Move.ROCK -> binding.player1Move.setImageDrawable(getDrawable(R.drawable.rock))
                Move.SCISSORS -> binding.player1Move.setImageDrawable(getDrawable(R.drawable.scissors))
                Move.PAPER -> binding.player1Move.setImageDrawable(getDrawable(R.drawable.paper))
            }
            val anim = AnimationUtils.loadAnimation(this@RPSActivity, android.R.anim.fade_in)
            binding.player1Move.animation = anim
            anim.start()
        }
    }

    private val onPlayer2MoveCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) {
            when (viewModel.player2Move.get()) {
                Move.ROCK -> binding.player2Move.setImageDrawable(getDrawable(R.drawable.rock))
                Move.SCISSORS -> binding.player2Move.setImageDrawable(getDrawable(R.drawable.scissors))
                Move.PAPER -> binding.player2Move.setImageDrawable(getDrawable(R.drawable.paper))
            }
            val anim = AnimationUtils.loadAnimation(this@RPSActivity, android.R.anim.fade_in)
            binding.player2Move.animation = anim
            anim.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rps)
        binding.viewModel = viewModel
        viewModel.player1Move.addOnPropertyChangedCallback(onPlayer1MoveCallback)
        viewModel.player2Move.addOnPropertyChangedCallback(onPlayer2MoveCallback)
//        viewModel.gameResult.addOnPropertyChangedCallback(onPlayer1MoveCallback)
    }
}
