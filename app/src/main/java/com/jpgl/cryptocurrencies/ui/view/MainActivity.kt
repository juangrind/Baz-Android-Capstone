package com.jpgl.cryptocurrencies.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jpgl.cryptocurrencies.databinding.ActivityMainBinding
import com.jpgl.cryptocurrencies.ui.viewModel.CryptoViewModel
import com.jpgl.cryptocurrencies.utils.BaseUtils
import com.jpgl.cryptocurrencies.utils.RequestState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cryptoViewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        BaseUtils.context = applicationContext
        setContentView(binding.root)

        cryptoViewModel.onCreateAvailableBook()
        cryptoViewModel.bookState.observe(this) {
            when (it) {
                is RequestState.Error -> Log.d("message", "AvailableBookError: ${it.message}")
                is RequestState.Loading -> Log.d("message", "AvailableBookLoading: $it")
                is RequestState.Success -> Log.d("message", "AvailableBookSuccess: ${it.data}")
            }
        }

        cryptoViewModel.bidsState.observe(this) {
            when (it) {
                is RequestState.Error -> Log.d("message", "BidsError: ${it.message}")
                is RequestState.Loading -> Log.d("message", "BidsLoading: $it")
                is RequestState.Success -> Log.d("message", "BidsSuccess: ${it.data}")
            }
        }

        cryptoViewModel.tickerState.observe(this) {
            when (it) {
                is RequestState.Error -> Log.d("message", "TickerError: ${it.message}")
                is RequestState.Loading -> Log.d("message", "TickerLoading: $it")
                is RequestState.Success -> Log.d("message", "TickerSuccess: ${it.data}")
            }
        }
    }
}
