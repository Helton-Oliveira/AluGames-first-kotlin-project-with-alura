package org.example

data class Game(
    private val title: String,
    private val template: String,
    private val description: String
) {

    override fun toString(): String {
        return "Game \n" +
                "title: '$title', \n" +
                "template: '$template'"
    }
}