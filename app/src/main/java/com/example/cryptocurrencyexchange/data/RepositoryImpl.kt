package com.example.cryptocurrencyexchange.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val items = sortedSetOf<ShopItem>({item1: ShopItem, item2:ShopItem ->
        item1.id - item2.id
    })

    private val _itemsLiveData = MutableLiveData<List<CurrencyItem>>()
    override val itemsLiveData: LiveData<List<CurrencyItem>>
        get() = _itemsLiveData

    override suspend fun getItem(currencyName: String): CurrencyItem {
        return items.find {
            it.currencyName == currencyName
        } ?: throw IllegalStateException("Item $currencyName isn't found")
    }

    init {
        items.add(CurrencyItem(currencyName = "Item_100", count = 40, id = current_id++, isActive = false))
        for (i in 1..4) {
            items.add(CurrencyItem(name = "Item_$i", count = i, id = current_id++))
        }
        items.add(CurrencyItem(name = "Item_100", count = 40, id = current_id++, isActive = false))

        update()
    }

    private fun update() {
        _itemsLiveData.value = items.toList()
    }

    override suspend fun addItem(item: CurrencyItem) {
        if (item.id == UNDEFINED_ID) {
            item.id = current_id++
        }
        items.add(item)

        update()
    }

    override suspend fun removeItem(item: CurrencyItem) {
        val itemToRemove = items.find {
            it.currencyName == item.currencyName
        }
        items.remove(itemToRemove)

        update()
    }

    override suspend fun changeItem(item: CurrencyItem) {
        val itemToChange = items.find {
            it.currencyName == item.currencyName
        }
        itemToChange ?: return

        items.remove(itemToChange)
        addItem(item)
    }

    companion object {
        private var current_id = 1
    }

}