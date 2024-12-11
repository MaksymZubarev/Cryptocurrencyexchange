package com.example.cryptocurrencyexchange.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.BeforeRaw
import com.example.cryptocurrencyexchange.domain.items.RawItem
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val cryptoCurrencyRetrofit = CryptoCurrencyRetrofit()

    private val items = sortedSetOf<CurrencyItem>({item1: CurrencyItem, item2:CurrencyItem ->
        (item1.currencyName!!.get(0).hashCode()-65)*26*26 +
                (item1.currencyName!!.get(1).hashCode()-65)*26 +
                (item1.currencyName!!.get(2).hashCode()-65) -
                (item2.currencyName!!.get(0).hashCode()-65)*26*26 -
                (item2.currencyName!!.get(1).hashCode()-65)*26 -
                (item2.currencyName!!.get(2).hashCode()-65)
    })

    private val _itemsLiveData = MutableLiveData<List<CurrencyItem>>()
    override val itemsLiveData: LiveData<List<CurrencyItem>>
        get() = _itemsLiveData

    override suspend fun getItem(currencyName: String): CurrencyItem {
        return items.find {
            it.currencyName == currencyName
        } ?: throw IllegalStateException("Item $currencyName isn't found")
    }

    private fun update() {
        _itemsLiveData.value = items.toList()
    }

    override suspend fun fetchData() {

        items.clear()

        cryptoCurrencyRetrofit.getCryptocurrencyData { response ->
            if (response != null) {
                val filteredData = response.data?.filterNotNull() ?: emptyList()

                Log.d("XXXX", "$filteredData")

                for (i in filteredData) {
                    if (i.rawData?.currencyItem!=null)
                        items.add(i.rawData?.currencyItem!!)
                }
                Log.d("XXXX", "$items")

                update()
            } else {
                Log.d("XXXX", "Failure")

                update()
            }
        }
    }
}
