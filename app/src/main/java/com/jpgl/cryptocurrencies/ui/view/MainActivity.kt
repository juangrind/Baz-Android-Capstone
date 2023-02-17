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
                is RequestState.Error -> Log.d("mensajito", "AvailableBookError: ${it.message}")
                is RequestState.Loading -> Log.d("mensajito", "AvailableBookLoading: $it")
                is RequestState.Success -> Log.d("mensajito", "AvailableBookSuccess: ${it.data}")
            }
        }

        cryptoViewModel.bidsState.observe(this) {
            when (it) {
                is RequestState.Error -> Log.d("mensajito", "BidsError: ${it.message}")
                is RequestState.Loading -> Log.d("mensajito", "BidsLoading: $it")
                is RequestState.Success -> Log.d("mensajito", "BidsSuccess: ${it.data}")
            }
        }

        cryptoViewModel.tickerState.observe(this) {
            when (it) {
                is RequestState.Error -> Log.d("mensajito", "TickerError: ${it.message}")
                is RequestState.Loading -> Log.d("mensajito", "TickerLoading: $it")
                is RequestState.Success -> Log.d("mensajito", "TickerSuccess: ${it.data}")
            }
        }
    }
}
