package com.example.test3dagcln.data.remote

import com.example.test3dagcln.data.remote.dto.CoinDto
import retrofit2.http.GET

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoinsList() : List<CoinDto>
}