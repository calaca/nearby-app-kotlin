package com.example.nearbyapp.ui.component.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nearbyapp.R
import com.example.nearbyapp.ui.theme.Typography

@Composable
fun WelcomeHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_logo_icon),
            contentDescription = "Ícone do app"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Boas vindas ao Nearby",
            style = Typography.headlineLarge
        )
        Text(
            text = "Tenha cupons de vantagem para usar em seus estabelecimentos favoritos.",
            style = Typography.bodyLarge
        )
    }
}