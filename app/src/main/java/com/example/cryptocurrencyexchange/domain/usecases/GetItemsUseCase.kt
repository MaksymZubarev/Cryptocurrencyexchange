package com.example.cryptocurrencyexchange.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.CurrencyItem
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() : LiveData<List<CurrencyItem>> {
        return repository.itemsLiveData
    }
}