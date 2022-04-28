package ru.ruslan.testpagin.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ruslan.testpagin.utils.baseURL

class ApiInstance {

    companion object {

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}