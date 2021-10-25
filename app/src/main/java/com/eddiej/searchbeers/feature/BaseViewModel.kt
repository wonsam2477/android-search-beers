package com.eddiej.searchbeers.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    val TAG: String = javaClass.simpleName

    private val _compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        _compositeDisposable.dispose()
        super.onCleared()
    }

    fun getDisposable(): CompositeDisposable = _compositeDisposable
    fun addDisposable(disposable: Disposable) {
        _compositeDisposable.add(disposable)
    }
}