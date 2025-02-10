package br.com.alura.alugames.util

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

// extension function
fun String.transformInAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyy")
    val birthdayDate = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    return Period.between(birthdayDate, today).years
}