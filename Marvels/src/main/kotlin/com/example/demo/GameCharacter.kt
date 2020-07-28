package com.example.demo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class GameCharacter(var name_: String, var characters: Array<Characters>) {

    fun getName(): String? {
        return name_
    }

   fun getCharacter(): Array<Characters>? {
        return characters
    }

}