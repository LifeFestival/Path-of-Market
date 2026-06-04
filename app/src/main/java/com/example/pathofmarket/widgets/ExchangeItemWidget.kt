package com.example.pathofmarket.widgets

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.pathofmarket.R
import com.example.pathofmarket.viemodel.ExchangeItem


@Composable
fun ExchangeItemWidget(item: ExchangeItem) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

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
                        ValueWidget(R.drawable.divine_icon, "Divine price: ${item.divinePrice ?: "-"}")
                        ValueWidget(R.drawable.exalted_icon, "Exalted price: ${item.exaltedPrice ?: "-"}")
                    }
                }
                ValueWidget(R.drawable.divine_icon, "Volume rate: ${item.volumePrimaryValue}")
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
            loading = { CircularProgressIndicator() },
            error = { e ->
                print(e.result.throwable.message)
                Text(e.result.throwable.message ?: "-")
            }
        )
    }
}

@Composable
private fun ItemName(name: String) {
    Text(name, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 16.dp))
}

@Composable
private fun ValueWidget(
    iconRes: Int,
    text: String,
) {
    Row {
        Icon(painter = painterResource(iconRes), null, tint = Color.Unspecified)
        Text(text, modifier = Modifier.padding(start = 8.dp))
    }
}