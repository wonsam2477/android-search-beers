package com.eddiej.searchbeers.data.source.remote.datasource

import androidx.paging.PagingData
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import io.reactivex.rxjava3.core.Observable

interface BeerRemoteDataSource {
    fun getBeers(beerName: String): Observable<PagingData<BeerItemEntity>>
}