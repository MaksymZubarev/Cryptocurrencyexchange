package com.example.cryptocurrencyexchange.domain.items

import com.google.gson.annotations.SerializedName

data class DataToRaw(
    @SerializedName("Data")
    val data: List<RawItem?>?
)