package com.eddiej.searchbeers.data.source.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.eddiej.searchbeers.data.source.remote.service.BeerService
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import com.eddiej.searchbeers.feature.main.search.BeerPagingSource
import io.reactivex.rxjava3.core.Observable

class BeerRemoteDataSourceImpl(private val service: BeerService) : BeerRemoteDataSource {
    override fun getBeers(beerName: String): Observable<PagingData<BeerItemEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25, // default size
                enablePlaceholders = true,
                prefetchDistance = 3
            ),
            pagingSourceFactory = { BeerPagingSource(beerName, service) })
            .observable
    }
}