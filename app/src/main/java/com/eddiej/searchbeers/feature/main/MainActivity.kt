package com.eddiej.searchbeers.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.data.source.remote.RetrofitClient
import com.eddiej.searchbeers.data.source.remote.service.BeerService
import com.eddiej.searchbeers.global.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitClient.get(this, Constants.API_ENDPOINT)
            .create(BeerService::class.java)

        service.getBeers("hite", 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("Test", "items count : ${result.size}")
            }, { error ->
                Log.d("Test", "error : ${error.message}")
            })
    }
}