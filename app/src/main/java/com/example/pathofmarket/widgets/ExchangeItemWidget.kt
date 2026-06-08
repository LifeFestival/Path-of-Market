package com.example.pathofmarket.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.pathofmarket.R
import com.example.pathofmarket.screens.state.ExchangeItem
import com.example.pathofmarket.theme.PathOfMarketColors
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.Line


@Composable
fun ExchangeItemWidget(item: ExchangeItem) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    2.dp,
                    Brush.linearGradient(
                        colors = listOf(
                            PathOfMarketColors.Primary.copy(alpha = 0.8f),
                            PathOfMarketColors.PrimaryVariant.copy(alpha = 0.5f)
                        )
                    )
                )
            )

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemIcon(item.image, 52.dp)
                ItemName(item.name)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(3f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        ValueWidget(
                            R.drawable.divine_icon,
                            "Divine price: ${item.divinePrice ?: "-"}"
                        )
                        ValueWidget(
                            R.drawable.exalted_icon,
                            "Exalted price: ${item.exaltedPrice ?: "-"}"
                        )
                    }
                }
                ValueWidget(R.drawable.divine_icon, "Volume/Hour: ${item.volumePrimaryValue}")
                SparklineWidget(item.totalChange, item.sparkline)
            }
        }
    }
}

@Composable
private fun ItemIcon(url: String, size: Dp) {
    Box(modifier = Modifier.size(size)) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = url,
            contentDescription = null,
            loading = { CircularProgressIndicator(color = PathOfMarketColors.Primary) },
            error = { e ->
                print(e.result.throwable.message)
                Text(e.result.throwable.message ?: "-", color = PathOfMarketColors.OnSurfaceVariant)
            }
        )
    }
}

@Composable
private fun ItemName(name: String) {
    Text(
        name,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = PathOfMarketColors.OnBackground,)
}

@Composable
private fun ValueWidget(
    iconRes: Int,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Icon(
            painter = painterResource(iconRes),
            null,
            tint = Color.Unspecified,
            modifier = Modifier.size(18.dp))
        Text(
            text,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = PathOfMarketColors.OnSurface)
    }
}

@Composable
private fun SparklineWidget(totalChange: Int, sparkline: List<Double>) {
    Box {
        LineChart(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            data = listOf(
                Line(
                    values = sparkline,
                    color = SolidColor(PathOfMarketColors.Primary)
                )
            ),
            dividerProperties = DividerProperties(enabled = true),
            gridProperties = GridProperties(enabled = false),
            indicatorProperties = HorizontalIndicatorProperties(enabled = false)
        )
        Text(
            "$totalChange%",
            color = if (totalChange > 0) Color(0xFF008400) else Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(end = 8.dp, bottom = 8.dp)
        )
    }
}