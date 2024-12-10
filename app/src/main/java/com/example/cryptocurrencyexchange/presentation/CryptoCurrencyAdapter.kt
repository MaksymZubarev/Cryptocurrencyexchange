package com.example.cryptocurrencyexchange.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexchange.R
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem


class CryptoCurrencyAdapter() :
    ListAdapter<CurrencyItem, CryptoCurrencyAdapter.CryptoViewHolder>(CryptoCurrencyDiffCallback()) {

    interface ItemsInteractionListener {
        fun onClick(currencyItem: CurrencyItem)
    }

    var itemsInteractionListener: ItemsInteractionListener? = null

    class CryptoViewHolder(
        val view: View,
    ) : RecyclerView.ViewHolder(view) {
        val cardView = view.findViewById<CardView>(R.id.cardView_currencyItem)
        val name = view.findViewById<TextView>(R.id.currencyName)
        val price = view.findViewById<TextView>(R.id.currencyRate)
        val lastUpdate = view.findViewById<TextView>(R.id.currencyUpdate)
//        val image = view.findViewById<TextView>(R.id.imageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.currency_item, viewGroup, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CryptoViewHolder, position: Int) {
        val currencyItem = getItem(position)
        viewHolder.name.text = currencyItem.currencyName
        viewHolder.price.text = currencyItem.price.toString()
        viewHolder.lastUpdate.text = currencyItem.lastUpdate.toString()

//        viewHolder.lastUpdate.value = "https://www.cryptocompare.com" + currencyItem.imageURL

        viewHolder.cardView.setOnClickListener {
            itemsInteractionListener?.onClick(currencyItem)
        }

    }
}