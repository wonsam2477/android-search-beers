package com.eddiej.searchbeers.data.repository

import androidx.paging.PagingData
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import io.reactivex.rxjava3.core.Observable

interface BeerRepository {
    fun getBeers(beerName: String): Observable<PagingData<BeerItemEntity>>
}