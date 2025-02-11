package br.com.alura.alugames.model

import java.math.BigDecimal

sealed class Plane(
    val type: String) {

    open fun getValue(rent: Rent): BigDecimal {
        return rent.game.price * rent.period.inDate.toBigDecimal()
    }
}