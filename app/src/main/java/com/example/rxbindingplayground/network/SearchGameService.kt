package com.example.rxbindingplayground.network

import com.example.rxbindingplayground.model.SearchedGamesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchGameService {
    companion object {
        const val BASE_URL = "https://api.rawg.io/api/"
        const val API_KEY = "4d601705c8324648b378d5e18e98d78e"
    }

    @GET("games?key=$API_KEY&page_size=3&platforms=4&page=1")
    fun getSearchedGameData(@Query("search") search: String): Observable<SearchedGamesResponse>
}