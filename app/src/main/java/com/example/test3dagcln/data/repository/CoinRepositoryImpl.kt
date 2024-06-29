package com.example.test3dagcln.data.repository

import com.example.test3dagcln.data.remote.CoinApi
import com.example.test3dagcln.data.remote.dto.CoinDto
import com.example.test3dagcln.domain.model.CoinModel
import com.example.test3dagcln.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {
    override suspend fun getCoinList(): List<CoinDto> {
        return api.getCoinsList()
    }
}