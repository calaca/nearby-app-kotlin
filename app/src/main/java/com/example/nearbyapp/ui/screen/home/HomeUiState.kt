package com.example.nearbyapp.ui.screen.home

import com.example.nearbyapp.data.model.Category
import com.example.nearbyapp.data.model.Market
import com.google.android.gms.maps.model.LatLng

data class HomeUiState(
    val categories: List<Category>? = null,
    val markets: List<Market>? = null,
    val marketLocations: List<LatLng>? = null
)
