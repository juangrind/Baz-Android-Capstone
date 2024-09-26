package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import com.jpgl.cryptocurrencies.utils.BaseUtils
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAvailableBookUseCaseTest {

    @RelaxedMockK
    private lateinit var cryptoRepository: CryptoRepository

    lateinit var getAvailableBookUseCase: GetAvailableBookUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mockkObject(BaseUtils)
        getAvailableBookUseCase = GetAvailableBookUseCase(cryptoRepository)
    }

    @Test
    fun `getAllAvailableBooksFromApi_whenNetworkConection_returnDataList`() = runBlocking {
        // given
        val myList = listOf(BooksModelDomain("btc_mxn", "40000", "20000000", "0.00000030000", "3000", "10.00", "200000000"))
        every { BaseUtils.isNetworkEnabled() } returns true
        coEvery { cryptoRepository.getAllAvailableBooksFromApi() } returns myList

        // when
        val response = getAvailableBookUseCase()

        // then
        coVerify(exactly = 1) { cryptoRepository.cleanAvailableBooks() }
        coVerify(exactly = 1) { cryptoRepository.insertAvailableBooks(any()) }
        assert(myList == response)
    }

    @Test
    fun `getAllAvailableBooksFromApi_whenNetworkConection_returnEmptyList`() = runBlocking {
        // given
        var myList = emptyList<BooksModelDomain>()
        every { BaseUtils.isNetworkEnabled() } returns true
        coEvery { cryptoRepository.getAllAvailableBooksFromApi() } returns myList

        // when
        val response = getAvailableBookUseCase()

        // then
        coVerify(exactly = 1) { cryptoRepository.getAllAvailableBooksFromDatabase() }
        assert(myList == response)
    }

    @Test
    fun `getAllAvailableBooksFromApi_whenNoInternetConection_retusnAList`() = runBlocking {
        // given
        val myList = listOf(BooksModelDomain("btc_mxn", "40000", "20000000", "0.00000030000", "3000", "10.00", "200000000"))
        every { BaseUtils.isNetworkEnabled() } returns false
        coEvery { cryptoRepository.getAllAvailableBooksFromDatabase() } returns myList

        // when
        val response = getAvailableBookUseCase()

        // then
        coVerify(exactly = 1) { cryptoRepository.getAllAvailableBooksFromDatabase() }
        assert(myList == response)
    }
}
