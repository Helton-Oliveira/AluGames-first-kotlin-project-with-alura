package br.com.alura.alugames.principal

import br.com.alura.alugames.model.Gamer

fun main() {
    val gamer1:Gamer = Gamer("Karla", "karla@email.com")
    val gamer2:Gamer = Gamer("Helton", "helton@emaiol.com", "02/09/2007", "user123")

    println(gamer1)
    println(gamer2)

    // scope function "let" (reage como um setter do java, ao utilizar "it" referencia o próprio objeto da variável para realizar a mutação)
    gamer1.let {
        it.birthday = "12/03/2004"
        it.user = "karla098"
    }.also {
        println(gamer1.idIntern)
    }
    gamer1.user = "kall"
    println(gamer1)
}