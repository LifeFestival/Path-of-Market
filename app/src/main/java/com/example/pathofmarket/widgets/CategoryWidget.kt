package com.example.pathofmarket.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pathofmarket.R
import com.example.pathofmarket.model.CategoryItem
import com.example.pathofmarket.theme.PathOfMarketColors

@Composable
fun CategoryItemWidget(item: CategoryItem, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            PathOfMarketColors.Primary.copy(alpha = 0.8f),
                            PathOfMarketColors.PrimaryVariant.copy(alpha = 0.5f)
                        )
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier.size(42.dp).background(
                    color = PathOfMarketColors.PrimaryVariant.copy(alpha = 0.2f),
                    shape = CircleShape
                )
            ) {
                Icon(
                    painter =
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