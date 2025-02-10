package org.example.br.com.alura.alugames.principal

import br.com.alura.alugames.service.ConsumeApi
import org.example.br.com.alura.alugames.model.Game
import java.util.*

fun main() {
    val reader: Scanner = Scanner(System.`in`);
    println("Digite um código de jogo para buascar: ");
    val message = reader.nextLine();

    val myInfoGame = ConsumeApi().searchGame(message)

    var myGame: Game? = null;
    // runCatching meothod
    val result = runCatching {
         myGame = Game(
            myInfoGame.info.title,
            myInfoGame.info.thumb);
    }

    result.onFailure {
        println("Jogo inexistente. Tente outro número.")
    }

    result.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N");
        val option = reader.nextLine()
        if (option.equals("S", true))  {
            println("Insira a descrição personalizada para o jogo: ");
            val descriptionPersonalized = reader.nextLine();
            myGame?.description = descriptionPersonalized;
        } else {
            myGame?.description = myGame?.title;
        }
    }
    println(myGame);

    result.onSuccess {
        println("Busca finalizada com sucesso...");
    }
}