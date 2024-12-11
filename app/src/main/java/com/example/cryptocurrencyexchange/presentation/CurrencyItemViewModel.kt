package com.example.cryptocurrencyexchange.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import com.example.cryptocurrencyexchange.domain.usecases.GetItemUseCase
import com.example.cryptocurrencyexchange.domain.usecases.GetItemsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyItemViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
    private val getItemsUseCase: GetItemsUseCase,
) : ViewModel() {


    private val _itemLiveData = MutableLiveData<CurrencyItem>()
    val itemLiveData: LiveData<List<CurrencyItem>>
        get() = getItemsUseCase()


    fun getItem(name: String) {
        viewModelScope.launch {
            val currencyItem = getItemUseCase(name)
            _itemLiveData.value = currencyItem
        }
    }
}