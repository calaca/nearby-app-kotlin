package com.example.nearbyapp.ui.component.category

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearbyapp.data.model.Category
import com.example.nearbyapp.ui.theme.Gray300
import com.example.nearbyapp.ui.theme.Gray400
import com.example.nearbyapp.ui.theme.GreenBase
import com.example.nearbyapp.ui.theme.Typography

@Composable
fun CategoryFilterChip(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean = false,
    onClick: (isSelected: Boolean) -> Unit
) {
    FilterChip(
        modifier = modifier
            .padding(2.dp)
            .heightIn(min = 36.dp),
        selected = isSelected,
        onClick = { onClick(!isSelected) },
        label = { Text(category.name, style = Typography.bodyMedium, color = if (isSelected) Color.White else Gray400) },
        elevation = FilterChipDefaults.elevatedFilterChipElevation(elevation = 8.dp),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            disabledBorderColor = Gray300,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp,
            selectedBorderColor = Color.Transparent
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = GreenBase,
        ),
        leadingIcon = {
            category.icon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = it),
                    tint = if (isSelected) Color.White else Gray400,
                    contentDescription = "Ícone de filtro de categoria",
                )
            }
        }
    )
}

@Preview
@Composable
private fun CategoryFilterChipViewPreview() {
    CategoryFilterChip(
        category = Category(
            id = "1",
            name = "Alimentação",
        ),
        isSelected = false,
        onClick = {}
    )
}

@Preview
@Composable
private fun CategoryFilterChipViewSelectedPreview() {
    CategoryFilterChip(
        category = Category(
            id = "2",
            name = "Alimentação",
        ),
        isSelected = true,
        onClick = {}
    )
}