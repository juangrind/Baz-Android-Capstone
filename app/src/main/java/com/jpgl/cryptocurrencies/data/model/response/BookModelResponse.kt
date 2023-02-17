package com.jpgl.cryptocurrencies.data.model.response

import com.google.gson.annotations.SerializedName
import com.jpgl.cryptocurrencies.data.model.BookModel

data class BookModelResponse(
    var success: Boolean,
    @SerializedName("payload") var bookData: ArrayList<BookModel>,
)
