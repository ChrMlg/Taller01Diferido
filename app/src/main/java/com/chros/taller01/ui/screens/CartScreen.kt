package com.chros.taller01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chros.taller01.Plate

@Composable
fun CartScreen(
    cart: List<Plate>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cart",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp,
                letterSpacing = 0.15.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(cart) { plate ->
                CartRow(plate)
            }
        }
    }
}

@Composable
fun CartRow(plate: Plate) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = plate.imageUri ?: plate.imageRes),
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = plate.name,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = "Quantity: 1", // You can adjust this part if you have a quantity field
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                letterSpacing = 0.15.sp
            )
        )
    }
}