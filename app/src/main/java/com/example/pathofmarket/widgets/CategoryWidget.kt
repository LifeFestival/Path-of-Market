package com.example.pathofmarket.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pathofmarket.R
import com.example.pathofmarket.screens.state.CategoryItem

@Composable
fun CategoryItemWidget(item: CategoryItem, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier.size(42.dp)
            ) {
                Icon(painter =
                    painterResource(item.iconRes),
                    null,
                    tint = Color.Unspecified,
                    modifier = Modifier.fillMaxSize(),
                    )
            }
            Text(item.name, fontSize = 22.sp, modifier = Modifier.padding(start = 16.dp))
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItemWidget(CategoryItem("Currency", R.drawable.divine_icon), {})
}