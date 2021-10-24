package com.eddiej.searchbeers.data.source.remote

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    fun get(
        context: Context,
        baseUrl: String,
        interceptors: List<Interceptor> = listOf()
    ): Retrofit {
        val httpClient = OkHttpClient.Builder()

        // 10mb
        val cacheSize = (10 * 512 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        httpClient.connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .retryOnConnectionFailure(true)

        if (interceptors.isNotEmpty()) {
            interceptors.forEach {
                httpClient.addInterceptor(it)
            }
        }

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }
}