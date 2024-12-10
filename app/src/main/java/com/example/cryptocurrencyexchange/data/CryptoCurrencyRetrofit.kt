package com.example.cryptocurrencyexchange.data

import com.example.cryptocurrencyexchange.domain.RetrofitInterface
import com.example.cryptocurrencyexchange.domain.items.DataToRaw
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoCurrencyRetrofit {

    // https://min-api.cryptocompare.com/data/top/totalvolfull?limit=30&tsym=USD

    private val BASE_URL = "https://min-api.cryptocompare.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RetrofitInterface::class.java)

    fun getCryptocurrencyData(resultCallback: (DataToRaw?) -> Unit) {

        val call = service.getCurrencyExchange()

        call?.enqueue(object : Callback<DataToRaw> {
            override fun onResponse(
                p0: Call<DataToRaw>,
                response: Response<DataToRaw>
            ) {
                resultCallback(response.body())
            }

            override fun onFailure(p0: Call<DataToRaw>, throwable: Throwable) {
                resultCallback(null)
            }

        })
    }
}