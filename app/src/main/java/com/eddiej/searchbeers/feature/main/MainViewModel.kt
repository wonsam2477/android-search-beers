package com.eddiej.searchbeers.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import com.eddiej.searchbeers.domain.usecase.GetBeerUseCase
import com.eddiej.searchbeers.feature.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val beerUseCase: GetBeerUseCase) : BaseViewModel() {
    private val _pagingData = MutableLiveData<PagingData<BeerItemEntity>>()
    val pagingData: LiveData<PagingData<BeerItemEntity>>
        get() = _pagingData

    fun getBeers(beerName: String) {
        val responseStream = beerUseCase
            .get(beerName)
            .cachedIn(viewModelScope)

        responseStream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _pagingData.value = it
            }
    }
}