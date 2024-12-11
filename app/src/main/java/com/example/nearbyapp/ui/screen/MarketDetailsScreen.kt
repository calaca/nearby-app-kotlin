package com.example.nearbyapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nearbyapp.data.model.Market
import com.example.nearbyapp.data.model.mock.mockMarkets
import com.example.nearbyapp.ui.component.button.Button
import com.example.nearbyapp.ui.component.market_details.MarketDetailsCoupons
import com.example.nearbyapp.ui.component.market_details.MarketDetailsInfo
import com.example.nearbyapp.ui.theme.Typography
import com.example.nearbyapp.R

@Composable
fun MarketDetailsScreen(modifier: Modifier = Modifier, market: Market, onNavigateBack: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do estabelecimento",
            contentScale = ContentScale.Crop,
            model = market.cover
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, 36.dp)
            ) {
                Column {
                    Text(text = market.name, style = Typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = market.description, style = Typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    MarketDetailsInfo(market = market)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
//                    if (market.rules.isNotEmpty()) {
//                        MarketDetailsRules(rules = market.rules)
//                        HorizontalDivider(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 16.dp)
//                        )
//                    }
                    MarketDetailsCoupons(coupons = listOf("AWEG17973", "AWEG17974", "AWEG17975"))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(
                        iconRes = R.drawable.ic_map_pin,
                        onClick = {}
                    )
                    Button(
                        modifier = Modifier.weight(1f),
                        text = "Ler QR Code",
                        iconRes = R.drawable.ic_scan,
                        onClick = { }
                    )
                }
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 56.dp, start = 24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )
    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(market = mockMarkets.first(), onNavigateBack = {})
}