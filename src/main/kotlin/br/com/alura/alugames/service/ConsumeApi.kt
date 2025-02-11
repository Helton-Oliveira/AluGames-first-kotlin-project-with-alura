package br.com.alura.alugames.service

import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.model.InfoGameJson
import br.com.alura.alugames.model.InfoGamerJson
import br.com.alura.alugames.util.createGame
import br.com.alura.alugames.util.createGamer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.example.br.com.alura.alugames.model.Game
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumeApi {

    private fun consumeData(url:String): String {
        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()
        val response = client
            .send(request, BodyHandlers.ofString())

        return response.body()
    }

    fun searchGame(): List<Game> {
        val gson = Gson();
        val url = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/jogos.json"
        val json = consumeData(url);
        val typeGame = object: TypeToken<List<InfoGameJson>>(){}.type
        val infoGames: List<InfoGameJson> = gson.fromJson(json, typeGame)

        return infoGames.map { infoGameJson -> infoGameJson.createGame() }
    }

    fun searchGamer(): List<Gamer> {
        val gson = Gson();
        val url = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"
        val json = consumeData(url)
        val typeGamer = object : TypeToken<List<InfoGamerJson>>() {}.type //type token para informar ao JSON que quero transformar um texto a uma classe
        val infoGamerList: List<InfoGamerJson> = gson.fromJson(json, typeGamer)

        val gamers  = infoGamerList.map {
            infoGamer -> infoGamer.createGamer()
        }
        return gamers
    }
}