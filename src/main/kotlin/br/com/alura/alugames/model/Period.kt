package br.com.alura.alugames.model

import java.time.LocalDate
import java.time.Period

data class Period(
    val initialDate: LocalDate,
    val finishDate: LocalDate) {
    val inDate = Period.between(initialDate, finishDate).days
}
