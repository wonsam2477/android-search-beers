package com.eddiej.searchbeers.feature.main.search

import androidx.fragment.app.activityViewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.databinding.FragmentBeerListBinding
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import com.eddiej.searchbeers.feature.BaseFragment
import com.eddiej.searchbeers.feature.main.MainViewModel
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BeerListFragment : BaseFragment<FragmentBeerListBinding>() {
    private val viewModel by activityViewModels<MainViewModel>()
    override fun setupViews() {
        viewModel.pagingData.observe(this, {entity ->
            createAdapter(binding?.recyclerView, entity)
        })
    }

    override fun bindViews() {
        binding?.let {
            it.viewModel = viewModel

            it.searchView.queryTextChanges()
                // 입력 후 1초 경과 시 Query 전달
                .debounce(1000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map { charSequence -> charSequence.toString() }
                // 빈 문자열이나 공백은 무시
                .filter { query -> !query.isNullOrBlank() }
                .subscribe { query ->
                    viewModel.getBeers(query)
                }
        }
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_beer_list

    private fun createAdapter(recyclerView: RecyclerView?, pagingData: PagingData<BeerItemEntity>) {
        recyclerView?.let { rv ->
            if (rv.adapter != null && rv.adapter is BeerListAdapter) {
                rv.adapter as BeerListAdapter
            } else {
                // PagingAdapter
                val adapter = BeerListAdapter()
                // LoadingAdapter
                val loadingAdapter = LoadingStateAdapter()
                // PagingAdapter 상태 연결
                adapter.addLoadStateListener { loadStates ->
                    loadingAdapter.loadState = loadStates.append
                }
                // 어댑터 결합
                val concatAdapter = ConcatAdapter(adapter, loadingAdapter)
                recyclerView.adapter = concatAdapter

                adapter.submitData(lifecycle, pagingData)
            }
        }
    }
}