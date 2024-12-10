package com.example.nearbyapp.ui.component.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearbyapp.data.model.Market
import com.example.nearbyapp.data.model.mock.mockMarkets
import com.example.nearbyapp.ui.theme.Typography

@Composable
fun MarketCardList(
    modifier: Modifier = Modifier,
    markets: List<Market>,
    onMarketClick: (Market) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(text = "Explore locais perto de você", style = Typography.bodyLarge)
        }
        items(markets, { it.id }) { market ->
            MarketCard(
                modifier = Modifier.fillParentMaxWidth(),
                market = market,
                onClick = { onMarketClick(market) }
            )
        }
    }
}

@Preview
@Composable
private fun MarketCardLintPreview() {
    MarketCardList(
        markets = mockMarkets,
        onMarketClick = {}
    )
}