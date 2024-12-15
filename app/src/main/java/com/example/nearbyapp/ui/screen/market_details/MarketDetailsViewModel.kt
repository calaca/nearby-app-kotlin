package com.example.nearbyapp.ui.screen.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearbyapp.core.network.RemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailsUiState())
    val uiState: StateFlow<MarketDetailsUiState> = _uiState.asStateFlow()

    fun onEvent(event: MarketDetailsUiEvent) {
        when (event) {
            is MarketDetailsUiEvent.OnFetchRules -> fetchRules(event.marketId)
            is MarketDetailsUiEvent.OnFetchCoupon -> fetchCoupon(event.qrCodeContent)
            is MarketDetailsUiEvent.OnResetCoupon -> resetCoupon()
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                RemoteDataSource.getMarketDetails(marketId).fold(
                    onSuccess = { marketDetails ->
                        currentUiState.copy(rules = marketDetails.rules)
                    },
                    onFailure = { _ ->
                        currentUiState.copy(rules = emptyList())
                    }
                )
            }
        }
    }

    private fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                RemoteDataSource.patchCoupon(qrCodeContent).fold(
                    onSuccess = { coupon ->
                        currentUiState.copy(coupon = coupon.coupon)
                    },
                    onFailure = { _ ->
                        currentUiState.copy(coupon = null)
                    }
                )
            }
        }
    }

    private fun resetCoupon() {
        _uiState.update { currentUiState ->
            currentUiState.copy(coupon = null)
        }
    }
}