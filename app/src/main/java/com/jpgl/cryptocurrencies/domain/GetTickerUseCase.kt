package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.domain.model.TickerModelDomain
import com.jpgl.cryptocurrencies.utils.BaseUtils
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) {

    suspend operator fun invoke(book: String): TickerModelDomain? {
        return try {
            val ticker = if (BaseUtils.isNetworkEnabled()) cryptoRepository.getAllTickerFromApi(book) else cryptoRepository.getAllTickerFromDatabase()
            if (ticker != null) {
                cryptoRepository.cleanTicker()
                cryptoRepository.insertTicker(ticker.toDatabase())
                ticker
            } else {
                cryptoRepository.getAllTickerFromDatabase()
            }
        } catch (exception: Exception) {
            // Manejar la excepción aquí
            null
        }
    }
}
