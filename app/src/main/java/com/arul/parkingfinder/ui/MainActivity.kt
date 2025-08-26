package com.arul.parkingfinder.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.arul.parkingfinder.ui.screens.MapScreen
import com.arul.parkingfinder.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                var hasLocation by remember { mutableStateOf(false) }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { result ->
                    hasLocation =
                        (result[Manifest.permission.ACCESS_FINE_LOCATION] == true) ||
                                (result[Manifest.permission.ACCESS_COARSE_LOCATION] == true)
                }

                LaunchedEffect(Unit) {
                    launcher.launch(arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ))
                }

                Surface(Modifier.fillMaxSize().semantics { contentDescription = "App Root" }) {
                    MapScreen(hasLocationPermission = hasLocation)
                }
            }
        }
    }
}
