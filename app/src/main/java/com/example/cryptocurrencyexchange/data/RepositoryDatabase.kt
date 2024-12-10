package com.example.cryptocurrencyexchange.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyexchange.data.db.CryptocurrencyEntity
import com.example.cryptocurrencyexchange.data.db.CryptocurrencyItemsRoomDatabase
import com.example.cryptocurrencyexchange.data.db.entitiesToItems
import com.example.cryptocurrencyexchange.data.db.toCryptocurrencyEntity
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import com.example.cryptocurrencyexchange.domain.items.RawItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryDatabase @Inject constructor(@ApplicationContext context: Context) : Repository {

    private val dao = CryptocurrencyItemsRoomDatabase.getDatabase(context).wordDao()

    private val cryptoCurrencyRetrofit = CryptoCurrencyRetrofit()

    private val _rawList = MutableLiveData<List<RawItem>>()
    val rawList: LiveData<List<RawItem>> get() = _rawList

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

    override suspend fun fetchData() {
        dao.deleteAll()
        cryptoCurrencyRetrofit.getCryptocurrencyData() { response ->
            if (response != null) {
                _rawList.postValue(response.data?.filterNotNull() ?: emptyList())
            } else {
                _rawList.postValue(emptyList())
            }
        }
        val currencyList = mutableListOf<CryptocurrencyEntity>()
        for (i in rawList.value!!) {
            currencyList.add(i.currencyItem!!.toCryptocurrencyEntity())
        }
        dao.addAll(currencyList)
    }

}