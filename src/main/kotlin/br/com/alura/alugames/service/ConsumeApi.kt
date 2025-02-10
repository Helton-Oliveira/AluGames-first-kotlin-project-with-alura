package br.com.alura.alugames.service

import com.google.gson.Gson
import org.example.br.com.alura.alugames.model.GameInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumeApi {

    fun searchGame(id:String): GameInfo {
        val gson = Gson();

        val url = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()
        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()
        val myInfoGame = gson.fromJson(json, GameInfo::class.java)

        return myInfoGame
    }
}