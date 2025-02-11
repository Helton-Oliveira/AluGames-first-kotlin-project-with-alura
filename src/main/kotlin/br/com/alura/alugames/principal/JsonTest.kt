package br.com.alura.alugames.principal

import br.com.alura.alugames.model.Period
import br.com.alura.alugames.service.ConsumeApi
import com.google.gson.GsonBuilder
import java.io.File
import java.time.LocalDate

fun main() {
    val consume = ConsumeApi()
    val gamers = consume.searchGamer()
    val games = consume.searchGame()

  /*  println(gamers)
    println(games)*/


    var gamerCaroline = gamers[3]
    val gameResidentEvil = games[10]
    val gameSpider = games[13]
    val game15 = games[15]
    val gameTheLastOfUs = games[2]

   /* gamerCaroline.rentGame(gameResidentEvil, Period(LocalDate.now(), LocalDate.now().plusDays(7)))
    gamerCaroline.rentGame(gameSpider, Period(LocalDate.now(), LocalDate.now().plusDays(10)))
    gamerCaroline.rentGame(game15, Period(LocalDate.now(), LocalDate.now().plusDays(3)))
    gamerCaroline.rentGame(gameTheLastOfUs, Period(LocalDate.now(), LocalDate.now().plusDays(10)))

    print(gamerCaroline.rentedGames)*/


    var gamerCamila = gamers[5]
    //gamerCamila.plane = SubscriptionPlane("PRATA", 9.90, 3)

    gamerCamila.rentGame(gameResidentEvil, Period(LocalDate.now(), LocalDate.now().plusDays(7)))
    gamerCamila.rentGame(gameSpider, Period(LocalDate.now(), LocalDate.now().plusDays(10)))
    gamerCamila.rentGame(game15, Period(LocalDate.now(), LocalDate.now().plusDays(3)))
    gamerCamila.rentGame(gameTheLastOfUs, Period(LocalDate.now(), LocalDate.now().plusDays(10)))
    println(gamerCamila.rentedGames)

    gamerCamila.recommend(7)
    gamerCamila.recommend(10)
    gamerCamila.recommend(8)

    gamerCamila.recommendGame(gameResidentEvil, 7)
    gamerCamila.recommendGame(gameTheLastOfUs, 10)
    gamerCaroline.recommendGame(gameResidentEvil, 7)
    gamerCaroline.recommendGame(gameTheLastOfUs, 9)

    /*print(gamerCamila.recommendedGames)
    print(gamerCaroline.recommendedGames)*/

    gamerCaroline = gamers.get(3)
    val jogoResidentVillage = games.get(10)
    val jogoSpider = games.get(13)
    val jogoTheLastOfUs = games.get(2)
    val jogoDandara = games.get(5)
    val jogoAssassins = games.get(4)
    val jogoCyber = games.get(6)
    val jogoGod = games.get(7)
    val jogoSkyrim = games.get(18)

    gamerCamila.recommendGame(jogoResidentVillage, 7)
    gamerCamila.recommendGame(jogoTheLastOfUs, 10)
    gamerCamila.recommendGame(jogoAssassins, 8)
    gamerCamila.recommendGame(jogoCyber, 7)
    gamerCamila.recommendGame(jogoGod, 10)
    gamerCamila.recommendGame(jogoDandara, 8)
    gamerCamila.recommendGame(jogoSkyrim, 8)
    gamerCamila.recommendGame(jogoSpider, 6)

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val serializer = gson.toJson(gamerCamila.recommendedGames)

    val archive = File("${gamerCamila.name}.json")
    archive.writeText(serializer)
    println(archive.absolutePath)
}