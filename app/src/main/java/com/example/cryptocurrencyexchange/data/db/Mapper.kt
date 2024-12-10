package com.example.cryptocurrencyexchange.data.db

import com.example.cryptocurrencyexchange.domain.items.CurrencyItem

fun CurrencyItem.toCryptocurrencyEntity() : CryptocurrencyEntity {
    return CryptocurrencyEntity(
        currencyName = this.currencyName ?: "N/A",
        lastMarket = this.lastMarket ?: "N/A",
        price = this.price ?: 0.0,
        lastUpdate = this.lastUpdate ?: 0,
        highday = this.highday ?: 0.0,
        lowday = this.lowday ?: 0.0,
        imageURL = this.imageURL ?: "N/A"
    )
}

fun entitiesToItems(entities: List<CryptocurrencyEntity>) = entities.map { it.toCryptocurrencyItem() }