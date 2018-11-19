package com.tesliukdev.fairgame.screens.rockpapscis.model

enum class Move {
    ROCK, SCISSORS, PAPER;

    fun doIBeat(move: Move): Boolean {
        return this == ROCK && move == SCISSORS
        || this == SCISSORS && move == PAPER
        || this == PAPER && move == ROCK
    }
}