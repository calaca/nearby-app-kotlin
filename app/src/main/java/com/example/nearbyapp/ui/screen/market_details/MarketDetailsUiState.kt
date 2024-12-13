package com.example.nearbyapp.ui.screen.market_details

import com.example.nearbyapp.data.model.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null
)
