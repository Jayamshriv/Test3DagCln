package com.example.test3dagcln.domain.usecase.coin_list_usecase

import android.util.Log
import com.example.test3dagcln.common.Resource
import com.example.test3dagcln.data.remote.dto.toCoinModel
import com.example.test3dagcln.domain.model.CoinModel
import com.example.test3dagcln.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val repository: CoinRepository
) {

     fun getCoins() : Flow<Resource<List<CoinModel>>> = flow {
         try {
             emit(Resource.Loading<List<CoinModel>>())
             val coins = repository.getCoinList().map { it.toCoinModel() }
             Log.v("List", coins.toString())
             emit(Resource.Success<List<CoinModel>>(coins))
         } catch(e: HttpException) {
             emit(Resource.Error<List<CoinModel>>(e.localizedMessage ?: "An unexpected error occured"))
         } catch(e: IOException) {
             emit(Resource.Error<List<CoinModel>>("Couldn't reach server. Check your internet connection."))
         }catch (e: Exception) {
             Log.e("CoinListUseCase", "Unknown exception: ${e.message}")
             emit(Resource.Error<List<CoinModel>>("An unknown error occurred"))
         }
     }
}