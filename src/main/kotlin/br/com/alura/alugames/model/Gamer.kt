package br.com.alura.alugames.model

import org.example.br.com.alura.alugames.model.Game
import java.util.*
import kotlin.random.Random

data class Gamer(val name:String, var email:String) : Recommended {
    var birthday:String? = null

    var user:String? = null
        set(value) {
            field = value
            if(idIntern.isNullOrBlank()) {
                generateIternalId()
            }
        }

    var idIntern:String? = null
        // properties
        private set

    val gameList = mutableListOf<Game?>()
    val rentedGames = mutableListOf<Rent>()
    var plane: Plane = SinglePlane("BRONZE")
    val notes = mutableListOf<Int>()
    val recommendedGames = mutableListOf<Game>()

    constructor(name:String, email:String, birthday:String, user:String) :
            this(name, email) {
                this.birthday = birthday
                this.user = user
                generateIternalId()
            }
    override val media: Double
        get() = notes.average()

    override fun recommend(note: Int) {
        if(note < 1 || note > 10) {
            println("Nota inválida, insira uma nota entre 0 e 10.")
        } else {
            notes.add(note)
        }
    }

    fun recommendGame(game: Game, note: Int) {
        game.recommend(note)
        recommendedGames.add(game)
    }

    // funcao (init) executa uma sequencia de algoritmos antes de construir o objeto
    init {
        if(name.isBlank()) {
            throw IllegalArgumentException("Nome está em branco.")
        }
        this.email = validateEmail();
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', birthday=$birthday, user=$user, idIntern=$idIntern)"
    }

    private fun generateIternalId() {
        val number:Int = Random.nextInt(10000)
        val tag = String.format("%04d", number)

       idIntern = "$user#$tag"
    }

    private fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido.")
        }
    }

    fun rentGame(game: Game, period: Period): Rent {
        val rentedGame = Rent(this, game, period)
        rentedGames.add(rentedGame)
        return rentedGame
    }

    fun getRentedGamesPerMonth(month:Int): List<Game> {
        val rentedGamesPerMonth = rentedGames.filter { rent -> rent.period.initialDate.monthValue == month }
        return rentedGamesPerMonth.map { rentedGame -> rentedGame.game }
    }

    companion object {
        fun createGamer(reader:Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val name = reader.nextLine()
            println("Digite seu e-mail:")
            val email = reader.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val option = reader.nextLine()

            if (option.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val birthday = reader.nextLine()
                println("Digite seu nome de usuário:")
                val user = reader.nextLine()

                return Gamer(name, email, birthday, user)
            } else {
                return Gamer(name, email)
            }

        }
    }
}

