package com.example.cryptocurrencyexchange.domain

import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    val currencyName: String?,
    val price: Double?,
    val min24hour: Double?,
    val max24hour: Double?,
    val lastDeal: String?,
    val lastUpdate: String?
)