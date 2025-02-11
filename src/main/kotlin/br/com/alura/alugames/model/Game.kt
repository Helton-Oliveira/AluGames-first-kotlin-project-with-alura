package org.example.br.com.alura.alugames.model

import br.com.alura.alugames.model.Recommended
import br.com.alura.alugames.util.formatDecimalNumbers
import com.google.gson.annotations.Expose
import java.math.BigDecimal

data class Game(
    @Expose val title: String,
    @Expose val template: String,
    var description:String
) : Recommended {
    val price = BigDecimal("0.0")
    val notes = mutableListOf<Int>()

    override val media: Double
        get() = notes.average().formatDecimalNumbers()

    override fun recommend(note: Int) {
        notes.add(note)
    }

    override fun toString(): String {
        return "Game(\n" +
                "title: '$title', \n " +
                "template: '$template', \n " +
                "price: ${price}, \n" +
                "description: '$description'\n" +
                "price: $price \n)"
    }
}