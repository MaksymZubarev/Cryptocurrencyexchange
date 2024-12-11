package com.example.cryptocurrencyexchange.domain.items

import com.google.gson.annotations.SerializedName

data class BeforeRaw(
    @SerializedName("RAW")
    val rawData: RawItem?
)