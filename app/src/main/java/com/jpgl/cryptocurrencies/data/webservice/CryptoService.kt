package com.jpgl.cryptocurrencies.data.webservice

import com.jpgl.cryptocurrencies.data.model.response.BidsModelResponse
import com.jpgl.cryptocurrencies.data.model.response.BookModelResponse
import com.jpgl.cryptocurrencies.data.model.response.TickerModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CryptoService @Inject constructor(
    private val apiClient: CryptoApiClient,
) {

    fun getAvailableBooks(): Observable<BookModelResponse> {
        val response = apiClient.getAvailableBooks().subscribeOn(Schedulers.io()).map { it.body()!! }
        return response
    }

    suspend fun getTicker(book: String): TickerModelResponse {
        return withContext(Dispatchers.IO) {
            val response = (apiClient).getTicker(book)
            response.body()!!
        }
    }

    suspend fun getOrderBooks(book: String): BidsModelResponse {
        return withContext(Dispatchers.IO) {
            val response = (apiClient).getOrderBook(book)
            response.body()!!
        }
    }
}
