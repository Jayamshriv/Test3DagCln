package com.example.test3dagcln.presentation.coin_list_screen

import com.example.test3dagcln.domain.model.CoinModel

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinModel> = emptyList(),
    val error: String = ""
)