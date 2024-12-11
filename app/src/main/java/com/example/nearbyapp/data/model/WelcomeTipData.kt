package com.example.nearbyapp.data.model

import androidx.annotation.DrawableRes

data class WelcomeTipData(
    val title: String,
    val subtitle: String,
    @DrawableRes val iconRes: Int
)
