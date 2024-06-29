package com.example.test3dagcln.domain.repository

import com.example.test3dagcln.data.remote.dto.CoinDto
import com.example.test3dagcln.domain.model.CoinModel

interface CoinRepository {

    suspend fun getCoinList() : List<CoinDto>

}