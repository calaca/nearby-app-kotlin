package com.example.nearbyapp.ui.component.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nearbyapp.R
import com.example.nearbyapp.data.model.WelcomeTipData
import com.example.nearbyapp.ui.theme.Typography

val welcomeTips = listOf(
    WelcomeTipData(
        "Encontre estabelecimentos",
        "Veja locais perto de você que são parceiros Nearby",
        R.drawable.ic_map_pin
    ),
    WelcomeTipData(
        "Ative o cupom com QR Code",
        "Escaneie o código no estabelecimento para usar o benefício",
        R.drawable.ic_qrcode
    ),
    WelcomeTipData(
        "Garanta vantagens perto de você",
        "Ative cupons onde estiver, em diferentes tipos de estabelecimentos",
        R.drawable.ic_ticket
    )
)

@Composable
fun WelcomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(text = "Veja como funciona:", style = Typography.bodyLarge)
        welcomeTips.forEach { tip ->
            WelcomeTip(
                modifier = Modifier.fillMaxWidth(),
                title = tip.title,
                subtitle = tip.subtitle,
                iconRes = tip.iconRes
            )
        }
    }
}