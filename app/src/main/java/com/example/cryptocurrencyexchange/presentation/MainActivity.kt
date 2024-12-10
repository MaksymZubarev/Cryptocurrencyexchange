package com.example.cryptocurrencyexchange.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyexchange.databinding.ActivityMainBinding
import com.example.cryptocurrencyexchange.domain.items.CurrencyItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "XXXX"


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    private val cryptoCurrencyAdapter = CryptoCurrencyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.itemsLiveData.observe(this) {
            Log.d(TAG, "getItemUseCase: $it")

            cryptoCurrencyAdapter.submitList(it)
        }



        binding.recyclerCurrency.layoutManager = LinearLayoutManager(this)
        binding.recyclerCurrency.adapter = cryptoCurrencyAdapter

        cryptoCurrencyAdapter.itemsInteractionListener = object : CryptoCurrencyAdapter.ItemsInteractionListener {
            override fun onClick(currencyItem: CurrencyItem) {
                Log.d(TAG, "onClick: $currencyItem")

                showInfoAboutItem(currencyItem)
            }
        }
    }

    private fun showInfoAboutItem(currencyItem: CurrencyItem) {
        val intent = Intent(this, CurrencyItemActivity::class.java)
        startActivity(intent)
    }

}