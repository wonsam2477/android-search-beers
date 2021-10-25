package com.eddiej.searchbeers.feature.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.searchbeers.databinding.LayoutLoadingBinding

class LoadingStateAdapter : LoadStateAdapter<LoadingStateAdapter.LoadingItemViewHolder>() {
    override fun onBindViewHolder(holder: LoadingItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutLoadingBinding.inflate(inflater, parent, false)

        return LoadingItemViewHolder(binding)
    }

    class LoadingItemViewHolder(private val binding: LayoutLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            // 로딩 여부에 따라 visibility 처리
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                loadingMessage.isVisible = loadState !is LoadState.Loading
            }

            binding.executePendingBindings()
        }
    }
}