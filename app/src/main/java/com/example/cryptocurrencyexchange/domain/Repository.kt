package com.example.cryptocurrencyexchange.domain

import androidx.lifecycle.LiveData
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem

interface Repository {
    val itemsLiveData: LiveData<List<CurrencyItem>>
    suspend fun getItem(name: String): CurrencyItem
    suspend fun fetchData()
}