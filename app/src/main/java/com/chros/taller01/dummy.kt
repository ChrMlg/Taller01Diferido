package com.chros.taller01

import android.net.Uri

data class Plate(val imageRes: Int, val name: String, var description: String, var price: String, var imageUri: Uri? = null)

val plates = listOf(
    Plate(R.drawable.plate1, "Plate 1", "Description 1", "10.00"),
    Plate(R.drawable.plate2, "Plate 2", "Description 2", "20.00"),
    Plate(R.drawable.plate3, "Plate 3", "Description 3", "30.00"),
    Plate(R.drawable.plate4, "Plate 4", "Description 4", "40.00"),
    Plate(R.drawable.plate5, "Plate 5", "Description 5", "50.00"),
    Plate(R.drawable.plate6, "Plate 6", "Description 6", "60.00"),
    Plate(R.drawable.plate7, "Plate 7", "Description 7", "70.00"),
    Plate(R.drawable.plate8, "Plate 8", "Description 8", "80.00")
)