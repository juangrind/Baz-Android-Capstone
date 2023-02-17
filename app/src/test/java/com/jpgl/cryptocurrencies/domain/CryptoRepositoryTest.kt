package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.webservice.CryptoService
import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CryptoRepositoryTest {

    @RelaxedMockK
    private lateinit var cryptoRepository: CryptoRepository
    private lateinit var cryptoService: CryptoService

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test repository`() = runBlocking {
        // coEvery { cryptoRepository.getAllAvailableBooksFromDatabase() }
        cryptoRepository.getAllAvailableBooksFromDatabase()
        coVerify { cryptoRepository.getAllAvailableBooksFromDatabase() }
    }

    @Test
    fun `test api`() = runBlocking {
        coEvery { cryptoRepository.getAllAvailableBooksFromApi() } returns emptyList<BooksModelDomain>()
        var myList = emptyList<BooksModelDomain>()

        myList = cryptoRepository.getAllAvailableBooksFromApi()
        println("size: ${myList.size}")
        assert(myList.size == 0)
    }
}
