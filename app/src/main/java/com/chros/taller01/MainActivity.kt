package com.chros.taller01

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chros.taller01.ui.screens.CartScreen
import com.chros.taller01.ui.screens.EditPlateScreen
import com.chros.taller01.ui.screens.MenuScreen
import com.chros.taller01.ui.theme.Taller01Theme

class MainActivity : ComponentActivity() {
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? by mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

        setContent {
            val navController = rememberNavController()
            val plates = remember { mutableStateListOf(*plates.toTypedArray()) }
            val cart = remember { mutableStateListOf<Plate>() }

            Taller01Theme {
                NavHost(navController = navController, startDestination = "menu") {
                    composable("menu") {
                        MenuScreen(navController, plates, cart) { updatedPlate ->
                            val index = plates.indexOfFirst { it.imageRes == updatedPlate.imageRes }
                            if (index != -1) {
                                plates[index] = updatedPlate
                            }
                        }
                    }
                    composable("edit_plate/{imageRes}") { backStackEntry ->
                        val imageRes = backStackEntry.arguments?.getString("imageRes")?.toInt() ?: 0
                        val plate = plates.firstOrNull { it.imageRes == imageRes } ?: return@composable
                        EditPlateScreen(
                            plate = plate,
                            onSave = { updatedPlate ->
                                val index = plates.indexOfFirst { it.imageRes == updatedPlate.imageRes }
                                if (index != -1) {
                                    plates[index] = updatedPlate
                                }
                                navController.popBackStack()
                            },
                            onPickImage = {
                                pickImageLauncher.launch("image/*")
                            },
                            selectedImageUri = selectedImageUri
                        )
                    }
                    composable("cart") {
                        CartScreen(cart)
                    }
                }
            }
        }
    }
}