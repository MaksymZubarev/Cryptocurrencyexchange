package com.example.cryptocurrencyexchange.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexchange.domain.usecases.GetItemsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val updateUseCase: UpdateUseCase
) : ViewModel() {

    val itemsLiveData
        get() = getItemsUseCase()

    fun updateInfo() {
        viewModelScope.launch {
            updateUseCase()
        }
    }
}