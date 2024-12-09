package com.example.cryptocurrencyexchange.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexchange.domain.CurrencyItem
import com.example.cryptocurrencyexchange.domain.usecases.GetItemUseCase
import com.example.cryptocurrencyexchange.domain.usecases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyItemViewModel @Inject constructor(
    private val updateUseCase: UpdateUseCase,
    private val getItemUseCase: GetItemUseCase,
) : ViewModel() {


    private val _itemLiveData = MutableLiveData<CurrencyItem>()
    val itemLiveData: LiveData<CurrencyItem>
        get() = _itemLiveData


    fun getItem(name: String) {
        viewModelScope.launch {
            val currencyItem = getItemUseCase(name)
            _itemLiveData.value = currencyItem
        }
    }
}