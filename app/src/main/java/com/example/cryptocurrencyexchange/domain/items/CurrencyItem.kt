package com.example.cryptocurrencyexchange.domain.items

import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    @SerializedName("FROMSYMBOL")
    val currencyName: String? = null,
    @SerializedName("LASTMARKET")
    val lastMarket: String? = null,
    @SerializedName("PRICE")
    val price: Double? = null,
    @SerializedName("LASTUPDATE")
    val lastUpdate: Int? = null,
    @SerializedName("HIGHDAY")
    val highday: Double? = null,
    @SerializedName("LOWDAY")
    val lowday: Double? = null,
    @SerializedName("IMAGEURL")
    val imageURL: String? = null
)