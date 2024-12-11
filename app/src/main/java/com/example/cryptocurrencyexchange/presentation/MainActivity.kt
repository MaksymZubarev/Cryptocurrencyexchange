package com.example.cryptocurrencyexchange.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexchange.R
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

    private var fragmentCurrency: FragmentContainerView? = null

    private val isPortrait: Boolean
        get() = fragmentCurrency == null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        fragmentCurrency = binding.currencyItemContainer

        viewModel.itemsLiveData.observe(this) {
            Log.d(TAG, "getItemUseCase: $it")

            cryptoCurrencyAdapter.submitList(it)
        }

        binding.recyclerCurrency.layoutManager = LinearLayoutManager(this)
        binding.recyclerCurrency.adapter = cryptoCurrencyAdapter

        binding.recyclerCurrency.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val position = layoutManager.findFirstVisibleItemPosition()
                val offset = layoutManager.findViewByPosition(position)?.top ?: 0
                viewModel.saveScrollPosition(position, offset)
            }
        })

        binding.recyclerCurrency.post {
            val layoutManager = binding.recyclerCurrency.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(viewModel.getScrollPosition(), viewModel.getScrollOffset())
        }

        cryptoCurrencyAdapter.itemsInteractionListener = object : CryptoCurrencyAdapter.ItemsInteractionListener {
            override fun onClick(currencyItem: CurrencyItem) {
                Log.d(TAG, "onClick: $currencyItem")

                if (isPortrait){
                    showInfoAboutItem(currencyItem.currencyName)
                } else {
                    currencyItem.currencyName?.let {
                        CryptoCurrencyFragment.newInstance(
                            it
                        )
                    }?.let { setupFragment(it) }
                }
            }
        }

        binding.btnFetch.setOnClickListener {
            viewModel.updateInfo()
        }
    }

    private fun showInfoAboutItem(currencyItemName: String?) {
        val intent = Intent(this, CurrencyItemActivity::class.java)
        intent.putExtra(CRYPTOITEMNAME, currencyItemName)
        startActivity(intent)
    }

    private fun setupFragment(fragment: CryptoCurrencyFragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.currency_item_container, fragment)
            .commit()

    }

}