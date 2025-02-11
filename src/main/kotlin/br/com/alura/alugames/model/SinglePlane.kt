package br.com.alura.alugames.model

import java.math.BigDecimal

class SinglePlane(type: String) : Plane(type) {
    override fun getValue(rent: Rent): BigDecimal {
        var originalValue = super.getValue(rent)
        if(rent.gamer.media > 8) { originalValue -= originalValue.multiply(BigDecimal("0.1")) }
        return originalValue
    }
}