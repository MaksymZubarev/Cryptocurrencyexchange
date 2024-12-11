package com.example.cryptocurrencyexchange.presentation

import androidx.lifecycle.SavedStateHandle
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
    private val updateUseCase: UpdateUseCase,
    private val state: SavedStateHandle
) : ViewModel() {

    val itemsLiveData
        get() = getItemsUseCase()

    private val _scrollPosition = state.getLiveData("scroll_position", 0)
    private val _scrollOffset = state.getLiveData("scroll_offset", 0)

    fun saveScrollPosition(position: Int, offset: Int) {
        state["scroll_position"] = position
        state["scroll_offset"] = offset
    }

    fun getScrollPosition(): Int = _scrollPosition.value ?: 0
    fun getScrollOffset(): Int = _scrollOffset.value ?: 0

    fun updateInfo() {
        viewModelScope.launch {
            updateUseCase()
        }
    }
}