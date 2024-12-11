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
    @SerializedName("LOWHDAY")
    val lowday: Double? = null,
    @SerializedName("IMAGEURL")
    val imageURL: String? = null
)

//"BTC"
//"CCCAGG"
//97511.6689743797
//1733824702
//98177.5815167446
//95625.0885867649
//"/media/37746251/btc.png"