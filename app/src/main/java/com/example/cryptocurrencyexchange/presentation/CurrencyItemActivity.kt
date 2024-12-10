package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyexchange.databinding.CurrencyItemFullBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrencyItemActivity : AppCompatActivity(){

    private val TAG: String = "XXXX"
    private val binding by lazy {
            CurrencyItemFullBinding.inflate(layoutInflater)
        }

    private val viewModel by viewModels<CurrencyItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupLiveData()
    }

    private fun setupLiveData() {
        viewModel.itemLiveData.observe(this) {
            with(binding) {
                cryptoCurrency.setText(it.currencyName)
                rateValue.setText(it.price.toString())
                maxRateValue.setText(it.highday.toString())
                minRateValue.setText(it.lowday.toString())
                lastDealValue.setText(it.lastMarket)
                updateValue.setText(it.lastUpdate.toString())
           }
        }
    }
}
