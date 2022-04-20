package com.example.rxbindingplayground.model

import com.squareup.moshi.Json

data class SearchedGame(
    @Json(name = "name")
    val name: String,
)