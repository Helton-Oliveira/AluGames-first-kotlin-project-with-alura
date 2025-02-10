package org.example.br.com.alura.alugames.principal

import com.google.gson.Gson
import org.example.br.com.alura.alugames.model.Game
import org.example.br.com.alura.alugames.model.GameInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

fun main() {
    val reader: Scanner = Scanner(System.`in`);
    println("Digite um código de jogo para buascar: ");
    val message = reader.nextLine();
    val url = "https://www.cheapshark.com/api/1.0/games?id=$message"

    val client: HttpClient = HttpClient.newHttpClient()
    val request: HttpRequest = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    val response = client
        .send(request, BodyHandlers.ofString());

    var json = response.body();

    val gson = Gson();
    var myInfoGame = gson.fromJson(json, GameInfo::class.java);

    // try/catch method

   /* try {
        val myGame = Game(myInfoGame.info.title, myInfoGame.info.thumb, "");
        println(myGame);
    } catch (ex: NullPointerException) {
        println("Jogo inexistente. Tente outro número.")
    }*/
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