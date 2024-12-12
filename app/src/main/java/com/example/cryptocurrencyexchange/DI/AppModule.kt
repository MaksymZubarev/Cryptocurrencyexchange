package com.example.cryptocurrencyexchange.DI

import com.example.cryptocurrencyexchange.data.RepositoryDatabase
import com.example.cryptocurrencyexchange.data.RepositoryImpl
import com.example.cryptocurrencyexchange.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

//    @Provides
//    @Singleton
//    fun provideRepository(repository: RepositoryImpl): Repository {
//        return repository
//    }

    @Binds
    @Singleton
    fun provideRepository(repository: RepositoryDatabase): Repository

}