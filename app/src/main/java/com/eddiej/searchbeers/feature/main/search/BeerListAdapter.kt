package com.eddiej.searchbeers.feature.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.databinding.BeerListItemBinding
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import com.eddiej.searchbeers.feature.main.MainViewModel

class BeerListAdapter(private val viewModel: MainViewModel) :
    PagingDataAdapter<BeerItemEntity, BeerListAdapter.BeerItemViewHolder>(BeerItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerListItemBinding.inflate(inflater, parent, false)

        return BeerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(viewModel, it)
        }
    }

    class BeerItemViewHolder constructor(private val binding: BeerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: MainViewModel, item: BeerItemEntity) {
            binding.setClickListener {
                viewModel.selectItem(item)
                it.findNavController().navigate(R.id.action_beerListFragment_to_beerDetailFragment)
            }
            binding.item = item
            binding.executePendingBindings()
        }
    }
}

class BeerItemDiffCallback : DiffUtil.ItemCallback<BeerItemEntity>() {
    override fun areItemsTheSame(oldItem: BeerItemEntity, newItem: BeerItemEntity): Boolean {
        // id 값으로 동일 아이템 여부 구분
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BeerItemEntity, newItem: BeerItemEntity): Boolean {
        return oldItem == newItem
    }
}