package com.example.nearbyapp.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearbyapp.data.model.Market
import com.example.nearbyapp.ui.component.category.CategoryFilterChipList
import com.example.nearbyapp.ui.component.market.MarketCardList
import com.example.nearbyapp.ui.theme.Gray100
import com.example.nearbyapp.ui.component.home.CustomGoogleMap
import com.example.nearbyapp.ui.component.home.SheetDragHandle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMarketDetails: (market: Market) -> Unit,
    selectedMarketId: String? = null
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    LaunchedEffect(key1 = true) {
        onEvent(HomeUiEvent.OnFetchCategories)
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetState,
        sheetContainerColor = Gray100,
        sheetPeekHeight = screenHeight * 0.35f,
        sheetShadowElevation = 16.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetDragHandle = { SheetDragHandle() },
        sheetContent = {
            if (!uiState.markets.isNullOrEmpty()) {
                MarketCardList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    markets = uiState.markets,
                    onMarketClick = { onNavigateToMarketDetails(it) }
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it
                            .calculateBottomPadding()
                            .minus(14.dp)
                    )
            ) {
                CustomGoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
                    selectedMarketId = selectedMarketId
                )
                if (!uiState.categories.isNullOrEmpty()) {
                    CategoryFilterChipList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 28.dp)
                            .align(Alignment.TopStart),
                        categories = uiState.categories,
                        onSelectedCategoryChanged = { selectedCategory ->
                            onEvent(HomeUiEvent.OnFetchMarkets(selectedCategory.id))
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onNavigateToMarketDetails = {}, uiState = HomeUiState(), onEvent = {})
}