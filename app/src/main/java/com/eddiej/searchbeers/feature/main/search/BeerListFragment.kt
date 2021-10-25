package com.eddiej.searchbeers.feature.main.search

import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.databinding.FragmentBeerListBinding
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import com.eddiej.searchbeers.feature.BaseFragment
import com.eddiej.searchbeers.feature.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BeerListFragment : BaseFragment<FragmentBeerListBinding>() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun setupViews() {

    }

    override fun bindViews() {
        viewModel.pagingData.observe(this, { entity ->
            createAdapter(binding?.recyclerView, entity)
        })

        binding?.let {
            it.viewModel = viewModel

            it.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        viewModel.getBeers(query)
                    }

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_beer_list

    private fun createAdapter(recyclerView: RecyclerView?, pagingData: PagingData<BeerItemEntity>) {
        recyclerView?.let { rv ->
            if (rv.adapter != null && rv.adapter is BeerListAdapter) {
                rv.adapter as BeerListAdapter
            } else {
                // PagingAdapter
                val adapter = BeerListAdapter(viewModel)
                // LoadingAdapter
                val loadingAdapter = LoadingStateAdapter {
                    adapter.retry()
                }
                // PagingAdapter 상태 연결
                adapter.addLoadStateListener { loadStates ->
                    loadingAdapter.loadState = loadStates.source.refresh

                    // 검색결과가 없을 때 간단한 텍스트 출력
                    val isEmpty =  (loadStates.source.refresh is LoadState.NotLoading && adapter.itemCount == 0)
                    binding?.textEmpty?.isVisible = isEmpty
                }

                // 어댑터 결합
                val concatAdapter = ConcatAdapter(adapter, loadingAdapter)
                recyclerView.adapter = concatAdapter

                adapter.submitData(lifecycle, pagingData)
            }
        }
    }
}