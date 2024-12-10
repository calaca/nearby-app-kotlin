package com.example.nearbyapp.data.model

import androidx.annotation.DrawableRes
import com.example.nearbyapp.R

enum class NearbyCategoryFilterChipView(
    val description: String,
    @DrawableRes val icon: Int
) {
    FOOD("Alimentação", R.drawable.ic_tools_kitchen_2),
    PURCHASES("Compras", R.drawable.ic_shopping_bag),
    ACCOMMODATION("Hospedagem", R.drawable.ic_bed),
    GROCERIES("Supermercado", R.drawable.ic_shopping_cart),
    ENTERTAINMENT("Cinema", R.drawable.ic_movie),
    PHARMACY("Farmácia", R.drawable.ic_first_aid_kit),
    FUEL("Combustível", R.drawable.ic_gas_station),
    BAKERY("Padaria", R.drawable.ic_bakery);

    companion object {
        fun fromDescription(description: String): NearbyCategoryFilterChipView? {
            return entries.find { it.description == description }
        }
    }
}