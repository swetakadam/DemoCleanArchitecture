package com.sweta.democleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.sweta.democleanarchitecture.ui.navigation.Navigation
import com.sweta.democleanarchitecture.ui.gallery.GalleryList
import com.sweta.democleanarchitecture.ui.gallery.GalleryViewModel
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailContract
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailView
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailViewModel
import com.sweta.democleanarchitecture.ui.maps.GeoMarkerViewModel
import com.sweta.democleanarchitecture.ui.mvisample.gallery.GalleryContract
import com.sweta.democleanarchitecture.ui.mvisample.gallery.MviGallery
import com.sweta.democleanarchitecture.ui.mvisample.gallery.MviGalleryViewModel
import com.sweta.democleanarchitecture.ui.navigation.DemoAppNavigation
import com.sweta.democleanarchitecture.ui.theme.DemoCleanArchitectureTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sweta.democleanarchitecture.ui.utils.locationFlow

class MainActivity : ComponentActivity() {
    //private val galleryViewModel:GalleryViewModel by viewModel()
    //private val galleryDetailViewModel:GalleryDetailViewModel by viewModel()

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    private val geoMarkerViewModel: GeoMarkerViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            DemoCleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    //GalleryList(viewModel = galleryViewModel, context = this)
                    DemoAppNavigation( navController = navController,
                        snackbarHostState = snackbarHostState,
                        geoMarkerViewModel = geoMarkerViewModel,
                        fetchLocationUpdates = ::fetchLocationUpdates)
                }
            }
        }
    }
    private fun fetchLocationUpdates() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fusedLocationClient.locationFlow().collect {
                    it?.let { location ->
                        geoMarkerViewModel.setCurrentLatLng(LatLng(location.latitude, location.longitude))
                    }
                }
            }
        }
    }
}
