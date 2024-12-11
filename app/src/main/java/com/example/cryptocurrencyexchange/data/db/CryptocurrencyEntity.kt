package com.example.cryptocurrencyexchange.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem

@Entity(tableName = "cryptocurrency_item_table")
data class CryptocurrencyEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name_item")
    val currencyName : String,

    @ColumnInfo(name = "lastmarket_item")
    val lastMarket: String,

    @ColumnInfo(name = "price_item")
    val price: Double,

    @ColumnInfo(name = "lastupdate_item")
    val lastUpdate: Int,

    @ColumnInfo(name = "highday_item")
    val highday: Double,

    @ColumnInfo(name = "lowday_item")
    val lowday: Double,

    @ColumnInfo(name = "imageurl_item")
    val imageURL: String
) {

    fun toCryptocurrencyItem() : CurrencyItem {
        return CurrencyItem(
            currencyName = this.currencyName,
            lastMarket = this.lastMarket,
            price = this.price,
            lastUpdate = this.lastUpdate,
            highday = this.highday,
            lowday = this.lowday,
            imageURL = this.imageURL
        )
    }
}