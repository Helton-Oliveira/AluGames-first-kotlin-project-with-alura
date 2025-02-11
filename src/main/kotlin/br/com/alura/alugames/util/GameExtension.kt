package br.com.alura.alugames.util

import br.com.alura.alugames.model.InfoGameJson
import org.example.br.com.alura.alugames.model.Game

fun InfoGameJson.createGame():Game {
    return Game(this.titulo, this.capa, this.preco, this.descricao)
}