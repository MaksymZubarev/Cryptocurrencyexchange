package com.example.cryptocurrencyexchange.domain

import com.example.cryptocurrencyexchange.domain.items.DataToRaw
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    // https://min-api.cryptocompare.com/data/top/totalvolfull?limit=30&tsym=USD

    @GET("data/top/totalvolfull?limit=30&tsym=USD")
    fun getCurrencyExchange(): Call<DataToRaw>?
}