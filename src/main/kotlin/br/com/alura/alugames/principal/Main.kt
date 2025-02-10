package br.com.alura.alugames.principal

import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.service.ConsumeApi
import br.com.alura.alugames.util.transformInAge
import org.example.br.com.alura.alugames.model.Game
import java.util.*

fun main() {
    val reader: Scanner = Scanner(System.`in`);
    val gamer = Gamer.createGamer(reader)
    println("Cadastro concluído com sucesso! Dados do gamer:")
    println(gamer)
    println("Idade do gamer: ${gamer.birthday?.transformInAge()}")

    do {
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
            gamer.gameList.add(myGame)
        }
        println("Deseja buscar um novo jogo? S/N")
        val response = reader.nextLine()
    } while (response.equals("s", true));

    println("Jogos buscados: ");
    println(gamer.gameList);

    println("\n Jogos ordenados por título")
    gamer.gameList.sortBy {
        it?.title
    }

    gamer.gameList.forEach {
        println("Título: ${it?.title}")
    }

    val gameFiltered = gamer.gameList.filter {
        it?.title?.contains("batman", true) ?: false
    }
    println("\n jogos filtrados:")
    println(gameFiltered)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opinion = reader.nextLine()
    if(opinion.equals("s", true)) {
        println(gamer.gameList)
        println("Informe a posição do jogo que deseja excluir: ")
        val position = reader.nextInt()
        gamer.gameList.removeAt(position)
    }
    println("\n Lista atualizada: ")
    println(gamer.gameList)

    println("Busca finalizada com sucesso...");
}
