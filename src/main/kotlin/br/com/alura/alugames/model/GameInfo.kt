package org.example.br.com.alura.alugames.model

data class GameInfo(val info: InfoApiShark) {

    override fun toString(): String {
        return info.toString();
    }
}