package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyexchange.R
import com.example.cryptocurrencyexchange.databinding.CurrencyItemFullBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrencyItemActivity : AppCompatActivity() {

    lateinit var binding: CurrencyItemFullBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CurrencyItemFullBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("XXX", binding.root.toString())

        val cryptoCurrencyItemName = intent.getStringExtra(CRYPTOITEMNAME)
        if (cryptoCurrencyItemName != null) {
            if (savedInstanceState == null) {
                setupFragment(CryptoCurrencyFragment.newInstance(cryptoCurrencyItemName))
            }
        }
    }

    private fun setupFragment(fragment: CryptoCurrencyFragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.currency_item_container, fragment)
            .commit()
    }
}