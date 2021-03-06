package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

/**
 * API's person object
 */
data class Person(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("job") val job: String
)
