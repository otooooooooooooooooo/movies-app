package com.example.movieapp.database.models

data class Filter(
    var genres: List<Int>? = null,
    var releaseYear: ReleaseYearInterval? = null,
    var director: Director? = null,
    var minRating: Double? = null,
    var duration: DurationInterval? = null
)