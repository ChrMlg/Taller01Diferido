package com.chros.taller01.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
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
    var newName by remember { mutableStateOf(plate.name) }
    var newPrice by remember { mutableStateOf(plate.price.toString()) } // Store price as a string to handle input
    var newDescription by remember { mutableStateOf(plate.description) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = newName,
            onValueChange = { newName = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = newDescription,
            onValueChange = { newDescription = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = newPrice,
            onValueChange = { newPrice = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = onPickImage) {
            Text("Pick Image")
        }
        selectedImageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(model = uri),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(top = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val updatedPlate = plate.copy(
                    name = newName,
                    price = newPrice.toDoubleOrNull() ?: plate.price,
                    imageUri = selectedImageUri?.toString()
                )
                onSave(updatedPlate)
            }
        ) {
            Text("Save")
        }
    }
}