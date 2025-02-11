package br.com.alura.alugames.model

import org.example.br.com.alura.alugames.model.Game

data class Rent(
    val gamer: Gamer,
    val game: Game,
    val period: Period) {
    private val rentValue: Double = gamer.plane.getValue(this)
    override fun toString(): String {
        return "Rented game ${game.title} by ${gamer.name} for the amount R$ $rentValue"
    }
}