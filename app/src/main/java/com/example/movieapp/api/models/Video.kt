package com.example.movieapp.api.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("name") val name: String,
    @SerializedName("key") val key: String,
    @SerializedName("site") val site: String,
    @SerializedName("type") val type: String,
)