package com.example.cryptocurrencyexchange.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.RawItem
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val cryptoCurrencyRetrofit = CryptoCurrencyRetrofit()

    private val _rawList = MutableLiveData<List<RawItem>>()
    val rawList: LiveData<List<RawItem>> get() = _rawList

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
        for (i in items) {
            val itemToRemove = items.find {
                it.currencyName == i.currencyName
            }
            items.remove(itemToRemove)
        }

        cryptoCurrencyRetrofit.getCryptocurrencyData() { response ->
            if (response != null) {
                _rawList.postValue(response.data?.filterNotNull() ?: emptyList())
            } else {
                _rawList.postValue(emptyList())
            }
        }

        for (i in rawList.value!!) {
            items.add(i.currencyItem!!)
        }

        update()
    }
}

