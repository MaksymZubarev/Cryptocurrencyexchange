package com.example.cryptocurrencyexchange.domain

import androidx.lifecycle.LiveData

interface Repository {
    val itemsLiveData: LiveData<List<CurrencyItem>>
    suspend fun getItem(name: String): CurrencyItem
    suspend fun Update()
}