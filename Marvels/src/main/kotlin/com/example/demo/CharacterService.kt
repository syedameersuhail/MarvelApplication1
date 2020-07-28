package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate
import java.time.Instant

open class service {

    var finalCharacters = mutableMapOf<String, Int>()
    private val log: org.slf4j.Logger? = LoggerFactory.getLogger(service::class.java)
    var restTemplate = RestTemplate()

    fun getfinalCharts() : MutableMap<String, Int>{
        return finalCharacters
    }

    fun getAvengerCharacters() {
        val start: Instant = Instant.now()
        println("Start of avengers $start")
        /*val avengerCharacter: GameCharacter? = restTemplate.getForObject("http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b", GameCharacter::class.java)
        var charts: Array<Characters>? = avengerCharacter?.getCharacter()*/
        val avengerCharacters = Array<Characters>(6) { Characters("", 0) }
        avengerCharacters[0] = Characters("Iron man", 60)
        avengerCharacters[1] = Characters("Captain America", 68)
        avengerCharacters[2] = Characters("Spider man", 58)
        avengerCharacters[3] = Characters("Black Panther", 68)
        avengerCharacters[4] = Characters("Vision", 50)
        avengerCharacters[5] = Characters("Hawk eye", 30)

        if (avengerCharacters != null) {
            synchronized(addCharacter(avengerCharacters)){
                addCharacter(avengerCharacters)
            }
        }
        val end: Instant = Instant.now()
        println("end of avengers $end")
    }

    fun getAntiHeros() {
        val anti = Array<Characters>(6) { Characters("", 0) }
        val start: Instant = Instant.now()
        println("Start of AntiHeros $start")
        anti[0] = Characters("Mandrin", 70)
        anti[1] = Characters("Thanos", 80)
        anti[2] = Characters("Galactus", 95)
        anti[3] = Characters("Hela", 75)
        anti[4] = Characters("Ego", 54)
        anti[5] = Characters("Dr. Doom", 78)
        if (anti != null) {
            synchronized(addCharacter(anti)) {
                addCharacter(anti)
            }
            val end: Instant = Instant.now()
            println("end of AntiHero $end")
        }
    }

    fun getMutants() {
        val start: Instant = Instant.now()
        println("Start of mutant $start")
        val mutants: GameCharacter? = restTemplate.getForObject("http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e", GameCharacter::class.java)
        val mutant: Array<Characters>? = mutants?.getCharacter()
       /* val Mutanscharacters = Array<Characters>(6) { Characters("", 0) }
        Mutanscharacters[0] = Characters("Apocalypse", 80)
        Mutanscharacters[1] = Characters("Professor", 75)
        Mutanscharacters[2] = Characters("Dark", 90)
        Mutanscharacters[3] = Characters("Magneto", 78)
        Mutanscharacters[4] = Characters("Wolverine", 64)
        Mutanscharacters[5] = Characters("Mystique", 66)*/

        if (mutant != null) {
            synchronized(addCharacter(mutant)) {
                addCharacter(mutant)
            }
        }

        val end: Instant = Instant.now()
        println("end of mutants $end")
    }

    /*
  @args : Array of GameCharacters
  @return : nothing
  * */
    fun addCharacter(Characters: Array<Characters>) {
        for (character in Characters) {
            if (finalCharacters.size < 16) {
                finalCharacters.put(character.name.toString(), character.max_power!!)
            } else {
                val minPower = finalCharacters.minBy { it.value }
                val characterWithMinPower = minPower?.key
                println(" **********Char wit low power $characterWithMinPower")
                finalCharacters.remove(characterWithMinPower)
                finalCharacters.put(character.name.toString(), character.max_power!!)
            }
        }
    }

    /*
   @args : name of game character
   @return : Power level of character
   * */
    fun getPowerLevel(name: String): String {
        val power: String
        if (finalCharacters.contains(name)) {
            power = finalCharacters[name].toString()
        } else {
            power = "Character not found"
        }
        return power
    }
}
