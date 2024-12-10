package com.example.cryptocurrencyexchange.domain.usecases

import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(itemName: String): CurrencyItem {
        return repository.getItem(itemName)
    }
}