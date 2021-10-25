package com.eddiej.searchbeers.data.source.remote.service

import com.eddiej.searchbeers.data.source.remote.model.beer.BeerItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerService {
    /**
     * Get beers
     * 검색어로 결과목록 가져오기
     * @param beerName 검색쿼리
     * @param page 페이지 번호
     * @return
     */
    @GET("beers/")
    fun getBeers(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int
    ): Single<List<BeerItem>>
}