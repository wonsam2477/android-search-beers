package com.eddiej.searchbeers.data.repository

import androidx.paging.PagingData
import com.eddiej.searchbeers.data.source.remote.datasource.BeerRemoteDataSource
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import io.reactivex.rxjava3.core.Observable

class BeerRepositoryImpl(private val remoteDataSource: BeerRemoteDataSource) : BeerRepository {
    override fun getBeers(beerName: String): Observable<PagingData<BeerItemEntity>> {
        return remoteDataSource.getBeers(beerName)
    }
}