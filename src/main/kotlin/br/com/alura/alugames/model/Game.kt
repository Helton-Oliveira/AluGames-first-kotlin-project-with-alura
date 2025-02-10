package org.example.br.com.alura.alugames.model

data class Game(
    val title: String,
    val template: String,
) {
    var description:String? = null;

    override fun toString(): String {
        return "Game: \n" +
                "title: $title \n" +
                "template: $template \n" +
                "Description: $description "
    }

}