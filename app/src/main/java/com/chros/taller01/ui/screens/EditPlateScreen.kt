package com.chros.taller01.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.chros.taller01.Plate

@Composable
fun EditPlateScreen(
    plate: Plate,
    onSave: (Plate) -> Unit,
    onPickImage: () -> Unit,
    selectedImageUri: Uri?
) {
    var name by remember { mutableStateOf(TextFieldValue(plate.name)) }
    var description by remember { mutableStateOf(TextFieldValue(plate.description)) }
    var price by remember { mutableStateOf(TextFieldValue(plate.price)) }
    var imageUri by remember { mutableStateOf(selectedImageUri ?: plate.imageUri) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUri ?: plate.imageRes),
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            onPickImage()
        }) {
            Text("Pick Image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val updatedPlate = plate.copy(
                name = name.text,
                description = description.text,
                price = price.text,
                imageUri = imageUri
            )
            onSave(updatedPlate)
        }) {
            Text("Save")
        }
    }
}