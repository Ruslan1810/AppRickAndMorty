package ru.ruslan.testpagin.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ruslan.testpagin.network.api.model.CharactersList

interface ApiService {

    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int): Response<CharactersList>
}