package com.sweta.democleanarchitecture.ui.maps.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapView(context: Context, location: LatLng) {

    // Camera Position State
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 16f)
    }

    val infoWindowState = rememberMarkerState(position = location)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ){
        Marker(
            state = MarkerState(position = location),
        )
        MarkerInfoWindow(
            state = infoWindowState,
            title = "My location",
            snippet = "Location custom info window",
            content = {
                CustomInfoWindow(title = it.title, description = it.snippet)
            }
        )
    }

}