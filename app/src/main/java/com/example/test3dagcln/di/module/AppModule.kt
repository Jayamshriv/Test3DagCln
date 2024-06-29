package com.example.test3dagcln.di.module

import android.util.Log
import android.widget.Toast
import com.example.test3dagcln.data.remote.CoinApi
import com.example.test3dagcln.data.remote.dto.CoinDto
import com.example.test3dagcln.data.repository.CoinRepositoryImpl
import com.example.test3dagcln.domain.model.CoinModel
import com.example.test3dagcln.domain.repository.CoinRepository
import com.example.test3dagcln.domain.usecase.coin_list_usecase.CoinListUseCase
import com.example.test3dagcln.presentation.coin_list_screen.viewmodel.CoinListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCoinAPI(): CoinApi {
        return try {
            Retrofit.Builder()
                .baseUrl("https://api.coinpaprika.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CoinApi::class.java)
        } catch (e: Exception) {
            // Handle exception appropriately (e.g., log error, throw custom exception)
            Log.e("Error",e.toString())
            throw IllegalStateException("Failed to create CoinApi instance", e)
        }
    }


    @Provides
    @Singleton
    fun providesRepository(api: CoinApi) : CoinRepository{
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCoinListUseCase(repository: CoinRepository) : CoinListUseCase{
        return CoinListUseCase(repository)
    }
}