package com.example.cryptocurrencyexchange.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem

class CryptoCurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyItem>() {
    override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
        return oldItem.currencyName == newItem.currencyName
    }

    override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
        return oldItem == newItem
    }
}