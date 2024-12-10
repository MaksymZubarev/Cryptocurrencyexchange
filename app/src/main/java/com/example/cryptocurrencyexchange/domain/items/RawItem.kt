package com.example.cryptocurrencyexchange.domain.items

import com.google.gson.annotations.SerializedName

data class RawItem(
    @SerializedName("RAW")
    val currencyItem: CurrencyItem?
)