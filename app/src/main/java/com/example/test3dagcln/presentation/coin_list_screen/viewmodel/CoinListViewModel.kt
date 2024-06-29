package com.example.test3dagcln.presentation.coin_list_screen.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test3dagcln.common.Resource
import com.example.test3dagcln.domain.model.CoinModel
import com.example.test3dagcln.domain.usecase.coin_list_usecase.CoinListUseCase
import com.example.test3dagcln.presentation.coin_list_screen.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    val useCase: CoinListUseCase
) : ViewModel(){

    private val _state = mutableStateOf(CoinListState())
    val state : State<CoinListState> = _state

    init {
//        _state.value = CoinListState()
        getCoins()
    }


    private fun getCoins() {
        useCase.getCoins().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                    Log.v("CoinList",result.data.toString())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                    Log.v("CoinList","..................Error.............${result.message}")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                    Log.v("CoinList","..................loading.............${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}