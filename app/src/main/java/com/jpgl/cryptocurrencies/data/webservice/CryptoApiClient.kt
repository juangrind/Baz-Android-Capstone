package com.jpgl.cryptocurrencies.data.webservice

import com.jpgl.cryptocurrencies.data.model.response.BidsModelResponse
import com.jpgl.cryptocurrencies.data.model.response.BookModelResponse
import com.jpgl.cryptocurrencies.data.model.response.TickerModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import io.reactivex.Single

interface CryptoApiClient {
    // Obtener libros disponibles
    @GET("available_books/")
    fun getAvailableBooks(): Observable<Response<BookModelResponse>>

    // Obtener info ticker
    @GET("ticker/")
    suspend fun getTicker(@Query("book") book: String): Response<TickerModelResponse>

    // Obtener order book
    @GET("order_book/")
    suspend fun getOrderBook(@Query("book") book: String): Response<BidsModelResponse>
}
