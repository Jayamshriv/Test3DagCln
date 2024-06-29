package com.example.test3dagcln.data.remote.dto

import com.example.test3dagcln.domain.model.CoinModel

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoinModel() : CoinModel{
    return CoinModel(
        id,is_active,name,rank,symbol
    )
}