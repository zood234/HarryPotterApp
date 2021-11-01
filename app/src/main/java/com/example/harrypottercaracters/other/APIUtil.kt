package com.example.harrypottercaracters

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetroInstance {
    companion object APIUtil {
        private val Base_URL = "http://hp-api.herokuapp.com/"

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}