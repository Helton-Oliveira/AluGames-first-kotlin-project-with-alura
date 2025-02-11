package br.com.alura.alugames.model

import java.math.BigDecimal

class SubscriptionPlane(
    type:String,
    val monthlyFee: BigDecimal,
    private val includedGames: Int,
    private val discountPercent: BigDecimal) : Plane(type) {

    override fun getValue(rent: Rent): BigDecimal {
        val totalGamesInMonth = rent
            .gamer
            .getRentedGamesPerMonth(rent.period.initialDate.monthValue)
            .size + 1

        return if(totalGamesInMonth <= includedGames) {
            BigDecimal("0.0")
        } else {
            var originalValue = super.getValue(rent)
            if (rent.gamer.media > 8 ) {
                originalValue -= originalValue.multiply(discountPercent)
            }
            originalValue
        }
    }
}