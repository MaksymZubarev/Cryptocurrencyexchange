package com.example.cryptocurrencyexchange.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.cryptocurrencyexchange.data.db.CryptocurrencyEntity
import com.example.cryptocurrencyexchange.data.db.CryptocurrencyItemsRoomDatabase
import com.example.cryptocurrencyexchange.data.db.entitiesToItems
import com.example.cryptocurrencyexchange.data.db.toCryptocurrencyEntity
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryDatabase @Inject constructor(@ApplicationContext context: Context) : Repository {

    private val dao = CryptocurrencyItemsRoomDatabase.getDatabase(context).wordDao()

    override val itemsLiveData: LiveData<List<CurrencyItem>>
        get() {
            val entityLiveData: LiveData<List<CryptocurrencyEntity>> = dao.itemsLiveData()

            val mediatorLiveData = MediatorLiveData<List<CurrencyItem>>()

            mediatorLiveData.addSource(entityLiveData) { entities ->
                mediatorLiveData.value = entitiesToItems(entities)
            }

            return mediatorLiveData
        }

    override suspend fun getItem(currencyName: String): CurrencyItem {
        return dao.getItem(currencyName).toCryptocurrencyItem()
    }

//    override suspend fun addItem(item: CurrencyItem) {
//        dao.addItem(item.toCryptocurrencyEntity())
//    }
//
//    override suspend fun removeItem(item: CurrencyItem) {
//        dao.removeItem(item.toCryptocurrencyEntity())
//    }
//
//    override suspend fun changeItem(item: CurrencyItem) {
//        dao.changeItem(item.toCryptocurrencyEntity())
//    }
}