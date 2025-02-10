package br.com.alura.alugames.model

import kotlin.random.Random

data class Gamer(val name:String, var email:String) {
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

    constructor(name:String, email:String, birthday:String, user:String) :
            this(name, email) {
                this.birthday = birthday
                this.user = user
                generateIternalId()
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
}

