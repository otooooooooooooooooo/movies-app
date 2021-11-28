package com.example.movieapp.api

import com.example.movieapp.models.Genre

object GenresList {
    var genres: List<Genre> = emptyList()

    fun whereId(id: Long): String? {
        return genres.find { g -> g.id == id }?.name
    }

    fun whereName(name: String): Long? {
        return genres.find { g -> g.name == name }?.id
    }
}