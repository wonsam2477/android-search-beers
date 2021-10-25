package com.eddiej.searchbeers.domain.usecase

import com.eddiej.searchbeers.data.repository.BeerRepository
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(private val beerRepository: BeerRepository) {
    fun get(beerName: String) = beerRepository.getBeers(beerName)
}