package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.domain.model.AsksModelDomain
import com.jpgl.cryptocurrencies.utils.BaseUtils
import javax.inject.Inject

class GetAsksUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) {
    suspend operator fun invoke(book: String): List<AsksModelDomain> {
        val asks = if (BaseUtils.isNetworkEnabled()) cryptoRepository.getAllAsksFromApi(book) else cryptoRepository.getAllAsksFromDatabase()

        return if (asks.isNotEmpty()) {
            cryptoRepository.cleanAsks()
            cryptoRepository.insertAsks(asks.map { it.toDatabase() })
            asks
        } else {
            cryptoRepository.getAllAsksFromDatabase()
        }
    }
}
