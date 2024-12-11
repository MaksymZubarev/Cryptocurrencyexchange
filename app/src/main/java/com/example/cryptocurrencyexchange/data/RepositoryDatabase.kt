package com.example.cryptocurrencyexchange.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyexchange.data.db.CryptocurrencyEntity
import com.example.cryptocurrencyexchange.data.db.CryptocurrencyItemsRoomDatabase
import com.example.cryptocurrencyexchange.data.db.entitiesToItems
import com.example.cryptocurrencyexchange.data.db.toCryptocurrencyEntity
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.BeforeRaw
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import com.example.cryptocurrencyexchange.domain.items.DataToRaw
import com.example.cryptocurrencyexchange.domain.items.RawItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    override suspend fun fetchData() {

        Log.d("YYYY", "proceeding")

        val cryptoCurrencyRetrofit = CryptoCurrencyRetrofit()

        val currencyList = mutableListOf<CryptocurrencyEntity>()

        val response = suspendCoroutine<DataToRaw?> { continuation ->
            cryptoCurrencyRetrofit.getCryptocurrencyData { result ->
                continuation.resume(result) } }

        if (response != null) {
            val filteredData = response.data?.filterNotNull() ?: emptyList()

            Log.d("YYYY", "$filteredData")

            for (i in filteredData) {
                if (i.rawData?.currencyItem!=null)
                    currencyList.add(i.rawData!!.currencyItem!!.toCryptocurrencyEntity())
            }
            Log.d("YYYY", "$currencyList")
        } else {
            Log.d("YYYY", "Failure")
        }

        Log.d("YYYY", "$currencyList")

        dao.deleteAll()

        dao.addAll(currencyList)
    }

}