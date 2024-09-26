package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.domain.model.BidsModelDomain
import com.jpgl.cryptocurrencies.utils.BaseUtils
import javax.inject.Inject

class GetBidsUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) {
    suspend operator fun invoke(book: String): List<BidsModelDomain> {
        return try {
            val bids = if (BaseUtils.isNetworkEnabled()) cryptoRepository.getAllBidsFromApi(book) else cryptoRepository.getAllBidsFromDatabase()
            if (bids.isNotEmpty()) {
                cryptoRepository.cleanBids()
                cryptoRepository.insertBids(bids.map { it.toDatabase() })
                bids
            } else {
                cryptoRepository.getAllBidsFromDatabase()
            }
        } catch (exception: Exception) {
            // Manejo de la excepción. Aquí puedes registrar el error, mostrar un mensaje al usuario, etc.
            cryptoRepository.getAllBidsFromDatabase()
        }
    }
}
