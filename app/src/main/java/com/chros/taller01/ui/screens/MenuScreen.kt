package com.chros.taller01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chros.taller01.Plate

@Composable
fun MenuScreen(
    navController: NavController,
    plates: List<Plate>,
    cart: MutableList<Plate>,
    onPlateUpdate: (Plate) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("cart") }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Menu",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 30.sp,
                    letterSpacing = 0.15.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(plates) { plate ->
                    PlateRow(plate, navController, cart)
                }
            }
        }
    }
}

@Composable
fun PlateRow(
    plate: Plate,
    navController: NavController,
    cart: MutableList<Plate>
) {
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
        Row {
            Button(onClick = { cart.add(plate) }) {
                Text(text = "Add to Cart")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate("edit_plate/${plate.imageRes}") }) {
                Text(text = "Edit")
            }
        }
    }
}