package com.example.rxbindingplayground.model

import com.squareup.moshi.Json

data class SearchedGamesResponse(
    @Json(name = "results")
    val results: List<SearchedGame>,
)