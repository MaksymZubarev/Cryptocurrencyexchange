package com.example.cryptocurrencyexchange.domain

import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    @SerializedName("currencyName")
    val baseCurrency: String?,
    @SerializedName("price")
    val currency: Double?,
    @SerializedName("min24hour")
    val purchaseRate: Double?,
    @SerializedName("max24hour")
    val purchaseRateNB: Double?,
    @SerializedName("lastDeal")
    val saleRate: String?,
    @SerializedName("lastUpdate")
    val saleRateNB: String?
)