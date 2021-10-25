package com.eddiej.searchbeers.di

import android.content.Context
import com.eddiej.searchbeers.data.repository.BeerRepository
import com.eddiej.searchbeers.data.repository.BeerRepositoryImpl
import com.eddiej.searchbeers.data.source.remote.RetrofitClient
import com.eddiej.searchbeers.data.source.remote.datasource.BeerRemoteDataSource
import com.eddiej.searchbeers.data.source.remote.datasource.BeerRemoteDataSourceImpl
import com.eddiej.searchbeers.data.source.remote.service.BeerService
import com.eddiej.searchbeers.global.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Singleton
    @Provides
    fun provideBeerService(@ApplicationContext context: Context): BeerService {
        return RetrofitClient.get(context, Constants.API_ENDPOINT)
            .create(BeerService::class.java)
    }

    @Singleton
    @Provides
    fun provideBeerRemoteDataSource(service: BeerService): BeerRemoteDataSource {
        return BeerRemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun provideBeerRepository(remoteDataSource: BeerRemoteDataSource): BeerRepository {
        return BeerRepositoryImpl(remoteDataSource)
    }
}