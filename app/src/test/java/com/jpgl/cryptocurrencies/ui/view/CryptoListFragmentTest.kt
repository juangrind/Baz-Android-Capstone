package com.jpgl.cryptocurrencies.ui.view

import com.jpgl.cryptocurrencies.ui.adapter.AvailableBooksAdapter
import com.jpgl.cryptocurrencies.ui.viewModel.CryptoViewModel
import io.mockk.*
import org.junit.After
import org.junit.Assert.*

class CryptoListFragmentTest {

    private val mockCryptoViewModel = mockk<CryptoViewModel>(relaxed = true)
    private val mockCryptoAdapter = mockk<AvailableBooksAdapter>(relaxed = true)

    @After
    fun tearDown() {
        clearAllMocks()
    }

   /* @Test
    fun `test onViewCreated`(){
        val target = spyk(createFragmentInstance())
        every { target.initWS() } returns Unit
        target.onViewCreated(mockk(relaxed = true), mockk(relaxed = true))
        verify (exactly = 1) {target.initWS()}
    }

    @Test
    fun `test initWS`() {
        val target = spyk(createFragmentInstance())
        val escenario = launchFragment { target }
        escenario.moveToState(Lifecycle.State.RESUMED)
        escenario.onFragment{ fragment ->
            verify { fragment.initWS() }
        }
        escenario.moveToState(Lifecycle.State.DESTROYED)
    }*/

    private fun createFragmentInstance(): CryptoListFragment {
        return CryptoListFragment()
    }
}
