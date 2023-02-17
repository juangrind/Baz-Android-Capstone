package com.jpgl.cryptocurrencies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jpgl.cryptocurrencies.databinding.CryptoBidsItemBinding
import com.jpgl.cryptocurrencies.databinding.CryptoAskItemBinding
import com.jpgl.cryptocurrencies.domain.model.AsksModelDomain

class AsksAdapter() : ListAdapter<AsksModelDomain, AsksAdapter.ViewHolder>(difCallback) {

    companion object {
        var difCallback = object : DiffUtil.ItemCallback<AsksModelDomain>() {
            override fun areItemsTheSame(oldItem: AsksModelDomain, newItem: AsksModelDomain): Boolean =
                oldItem.bookAsks == newItem.bookAsks
            override fun areContentsTheSame(oldItem: AsksModelDomain, newItem: AsksModelDomain): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = CryptoAskItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.enlazarItem(getItem(position))
    }

    class ViewHolder(val binding: CryptoAskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun enlazarItem(asksModel: AsksModelDomain) {
            binding.txtPriceAsks.text = "Price = $ ${asksModel.priceAsks}.00"
            binding.txtAmountAsks.text = "  ->   Amount = ${asksModel.amountAsks}"
        }
    }
}
