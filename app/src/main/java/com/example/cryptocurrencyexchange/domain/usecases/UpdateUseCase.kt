package com.example.cryptocurrencyexchange.domain.usecases


import com.example.cryptocurrencyexchange.domain.Repository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.Update()
    }
}