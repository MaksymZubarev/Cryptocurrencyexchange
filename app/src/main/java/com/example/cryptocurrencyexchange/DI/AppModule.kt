package com.example.cryptocurrencyexchange.DI

import com.example.cryptocurrencyexchange.data.RepositoryDatabase
import com.example.cryptocurrencyexchange.data.RepositoryImpl
import com.example.cryptocurrencyexchange.domain.Repository
import com.example.cryptocurrencyexchange.domain.usecases.GetItemsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.UpdateUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

//    @Provides
//    fun provideRepository(): Repository {
//        return RepositoryImpl()
//    }
//
//    @Provides
//    fun provideGetItemsUseCase(repository: Repository): GetItemsUseCase {
//        return GetItemsUseCase(repository)
//    }
//
//    @Provides
//    fun provideUpdateUseCase(repository: Repository): UpdateUseCase {
//        return UpdateUseCase(repository)
//    }
//
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://api.example.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Binds
    @Singleton
    fun provideRepository(repository: RepositoryDatabase): Repository

}