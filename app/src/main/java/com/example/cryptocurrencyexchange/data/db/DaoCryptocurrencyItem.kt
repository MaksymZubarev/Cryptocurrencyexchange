package com.example.cryptocurrencyexchange.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoCryptocurrencyItem {

    @Query("SELECT * FROM cryptocurrency_item_table")
    fun itemsLiveData(): LiveData<List<CryptocurrencyEntity>>
    @Query("SELECT * FROM cryptocurrency_item_table WHERE name_item LIKE :currencyName LIMIT 1")
    suspend fun getItem(currencyName: String): CryptocurrencyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(items: List<CryptocurrencyEntity>)

    @Query("DELETE FROM cryptocurrency_item_table")
    suspend fun deleteAll()

}