package com.eddiej.searchbeers.feature.main.search

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.eddiej.searchbeers.data.source.remote.model.beer.BeerItem
import com.eddiej.searchbeers.data.source.remote.service.BeerService
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import io.reactivex.rxjava3.core.Single

class BeerPagingSource(private val beerName: String, private val service: BeerService): RxPagingSource<Int, BeerItemEntity>() {
    override fun getRefreshKey(state: PagingState<Int, BeerItemEntity>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BeerItemEntity>> {
        // page number (기본값 : 1)
        val nextKey = params.key ?: 1

        val request = service.getBeers(beerName = beerName, page = nextKey)

        return request
            .map { data -> toResult(data, nextKey) }
    }

    private fun toResult(response: List<BeerItem>, nextKey: Int):LoadResult<Int,BeerItemEntity>{
        val data = response.map { item -> item.toEntity() }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = nextKey.plus(1)
        )
    }
}