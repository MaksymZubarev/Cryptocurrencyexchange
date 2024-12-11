package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyexchange.R
import com.example.cryptocurrencyexchange.databinding.FragmentCurrencyBinding
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class CryptoCurrencyFragment() : Fragment() {

    private val TAG: String = "XXXX"
    lateinit var binding: FragmentCurrencyBinding

    lateinit var viewModel: CurrencyItemViewModel

    private var cryptoCurrencyItemName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CurrencyItemViewModel::class.java]
        Log.d(TAG, "onViewCreated: ")
        viewModel.itemLiveData.observe(viewLifecycleOwner) {
            val cryptoItem = it.find { it.currencyName == cryptoCurrencyItemName }
            cryptoItem?.let {
                setupLiveData(cryptoItem)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with (requireArguments()) {
            cryptoCurrencyItemName = getString(CRYPTOITEMNAME).toString()
        }
        Log.d(TAG, "onCreate: $cryptoCurrencyItemName")
    }

    private fun setupLiveData(currencyItem: CurrencyItem) {
            with(binding) {
                Log.d(TAG, "setupLiveData: $currencyItem.currencyName")
                cryptoCurrency.text = currencyItem.currencyName
                rateValue.text = currencyItem.price.toString()
                maxRateValue.text = currencyItem.highday.toString()
                minRateValue.text = currencyItem.lowday.toString()
                lastDealValue.text = currencyItem.lastMarket
                val simpleDate = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                simpleDate.timeZone = TimeZone.getDefault()
                updateValue.text = simpleDate.format(Date(currencyItem.lastUpdate!!.toLong() * 1000))
                Picasso.get()
                    .load("https://www.cryptocompare.com" + currencyItem.imageURL)
                    .into(currencyImageFull)
            }
    }

    companion object {
        fun newInstance(name: String) = CryptoCurrencyFragment().apply {
            Log.d(TAG, "newInstance: $name")
            arguments = Bundle().apply {
                putString(CRYPTOITEMNAME, name)
            }
        }
    }
}

