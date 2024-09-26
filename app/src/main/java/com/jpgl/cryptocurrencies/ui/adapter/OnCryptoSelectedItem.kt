package com.jpgl.cryptocurrencies.ui.adapter

import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain

interface OnCryptoSelectedItem {

    fun onItemListener(booksModelDomain: BooksModelDomain)
}
