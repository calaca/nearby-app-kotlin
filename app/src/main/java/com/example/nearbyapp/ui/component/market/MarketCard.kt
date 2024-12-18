package com.example.nearbyapp.ui.component.market

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.nearbyapp.data.model.Market
import com.example.nearbyapp.ui.theme.Gray100
import com.example.nearbyapp.ui.theme.Gray200
import com.example.nearbyapp.R
import com.example.nearbyapp.data.model.mock.mockMarkets
import com.example.nearbyapp.ui.theme.Gray400
import com.example.nearbyapp.ui.theme.Gray500
import com.example.nearbyapp.ui.theme.RedBase
import com.example.nearbyapp.ui.theme.Typography

@Composable
fun MarketCard(
    modifier: Modifier = Modifier,
    market: Market,
    onClick: (Market) -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Gray100)
            .border(1.dp, Gray200, RoundedCornerShape(12.dp)),
        onClick = { onClick(market) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray100)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth(0.3f)
                    .heightIn(min = 100.dp)
                    .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true),
                model = market.cover,
                contentScale = ContentScale.Crop,
                contentDescription = "Imagem do estabelecimento",
            )
            Column {
                Text(
                    text = market.name,
                    style = Typography.headlineSmall.copy(fontSize = 14.sp),
                    color = Gray500,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = market.description,
                    style = Typography.bodyLarge.copy(fontSize = 12.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray500
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        tint = if (market.coupons > 0) RedBase else Gray400,
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = "Ícone de cupom"
                    )
                    Text(
                        text = if (market.coupons > 0) "${market.coupons} cupons disponíveis" else "Nenhum cupom disponível",
                        style = Typography.bodyMedium.copy(fontSize = 12.sp),
                        color = Gray400
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MarketCardPreview() {
    MarketCard(
        modifier = Modifier.fillMaxWidth(),
        market = mockMarkets.first(),
        onClick = {}
    )
}

@Preview
@Composable
private fun MarketCardNoCouponPreview() {
    MarketCard(
        modifier = Modifier.fillMaxWidth(),
        market = mockMarkets.last(),
        onClick = {}
    )
}