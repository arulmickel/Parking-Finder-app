package com.arul.parkingfinder.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.arul.parkingfinder.R
import com.arul.parkingfinder.data.repository.ParkingRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import com.arul.parkingfinder.util.ConnectivityObserver
import com.arul.parkingfinder.util.NetworkStatus

@Composable
fun MapScreen(hasLocationPermission: Boolean) {
    val ctx = LocalContext.current
    val repo = remember { ParkingRepository(ctx) }
    val scope = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState()

    // Collect pins from DB
    val lots by repo.observe().collectAsState(initial = emptyList())

    // First run: seed demo data
    LaunchedEffect(Unit) { repo.seedDemo() }

    GoogleMap(
        modifier = Modifier.fillMaxSize()
            .semantics { contentDescription = ctx.getString(R.string.map_content_desc) },
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
        uiSettings = MapUiSettings(myLocationButtonEnabled = true, zoomControlsEnabled = false)
    ) {
        lots.forEach { lot ->
            Marker(
                state = MarkerState(position = LatLng(lot.lat, lot.lng)),
                title = lot.name,
                snippet = "$${lot.pricePerHour}/hr"
            )
        }
    }
}

// inside MapScreen(...)
val snackbarHostState = remember { SnackbarHostState() }
var net by remember { mutableStateOf<NetworkStatus>(NetworkStatus.Available) }

LaunchedEffect(Unit) {
    ConnectivityObserver(ctx).observe().collect {
        net = it
        if (it is NetworkStatus.Unavailable) {
            snackbarHostState.showSnackbar(ctx.getString(R.string.offline_banner))
        } else {
            // try to refresh again when back online
            runCatching { repo.refreshFromNetwork() }
        }
    }
}

Scaffold(
topBar = { TopAppBar(title = { Text("Parking Finder") }) },
snackbarHost = { SnackbarHost(snackbarHostState) }
) { padding ->
    // put your GoogleMap here (same as before), inside a Box with Modifier.padding(padding)
}
