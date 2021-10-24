package com.eddiej.searchbeers.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    companion object {
        val TAG: String = this::class.java.simpleName
    }

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