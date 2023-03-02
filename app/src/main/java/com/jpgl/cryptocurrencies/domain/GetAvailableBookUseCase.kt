package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import com.jpgl.cryptocurrencies.utils.BaseUtils
import javax.inject.Inject

class GetAvailableBookUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) {
    suspend operator fun invoke(): List<BooksModelDomain> {
        return try {
            val books = if (BaseUtils.isNetworkEnabled()) {
                cryptoRepository.getAllAvailableBooksFromApi()
            } else {
                cryptoRepository.getAllAvailableBooksFromDatabase()
            }
            if (books.isNotEmpty()) {
                cryptoRepository.cleanAvailableBooks()
                cryptoRepository.insertAvailableBooks(books.map { it.toDatabase() })
                books
            } else {
                cryptoRepository.getAllAvailableBooksFromDatabase()
            }
        } catch (exception: Exception) {
            // Manejar la excepción aquí
            emptyList()
        }
    }
}
