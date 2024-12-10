package com.example.cryptocurrencyexchange.domain.items

import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    @SerializedName("FROMSYMBOL")
    val currencyName: String?,
    @SerializedName("LASTMARKET")
    val lastMarket: String?,
    @SerializedName("PRICE")
    val price: Double?,
    @SerializedName("LASTUPDATE")
    val lastUpdate: Int?,
    @SerializedName("HIGHDAY")
    val highday: Double?,
    @SerializedName("LOWHDAY")
    val lowday: Double?,
    @SerializedName("IMAGEURL")
    val imageURL: String?
)

//"BTC"
//"CCCAGG"
//97511.6689743797
//1733824702
//98177.5815167446
//95625.0885867649
//"/media/37746251/btc.png"