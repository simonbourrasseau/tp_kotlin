package com.example.tp_projet_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitSingleton {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://s519716619.onlinehome.fr/exchange/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(WSInterface::class.java)

    val courseRequest = service.listCar()
}