package com.sweta.democleanarchitecture.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sweta.democleanarchitecture.ui.gallery.GalleryList
import com.sweta.democleanarchitecture.ui.gallery.GalleryViewModel
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailContract
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailView
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailViewModel
import com.sweta.democleanarchitecture.ui.maps.GeoMarkerViewModel
import com.sweta.democleanarchitecture.ui.maps.composables.MapsScreen
import com.sweta.democleanarchitecture.ui.mvisample.gallery.GalleryContract
import com.sweta.democleanarchitecture.ui.mvisample.gallery.MviGallery
import com.sweta.democleanarchitecture.ui.mvisample.gallery.MviGalleryViewModel
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoAppNavigation(navController: NavHostController,
                      snackbarHostState: SnackbarHostState,
                      geoMarkerViewModel: GeoMarkerViewModel,
                      fetchLocationUpdates: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Navigation.Route.GALLERY_LIST) {
        composable(route = Navigation.Route.GALLERY_LIST) {
            //GalleryListScreen(navController)
            MviGalleryListScreen(navController)
        }
        composable(
            route = Navigation.Route.GALLERY_DETAILS,
            arguments = listOf(navArgument(Navigation.Arg.GALLERY_DETAIL_ID) {
                type = NavType.IntType
            })
        ) {
            // FoodCategoryDetailsDestination()
                backStackEntry ->
            GalleryDetailScreen(
                navController = navController,
                backStackEntry.arguments?.getInt(Navigation.Arg.GALLERY_DETAIL_ID) ?: 1
            )
        }
        composable(route = Navigation.Route.MAP_VIEW){
            MapsScreen(
                snackbarHostState = snackbarHostState,
                navController = navController,
                fetchLocationUpdates = fetchLocationUpdates
            )

        }
    }
}

@Composable
private fun GalleryListScreen(navController: NavHostController) {
    val galleryViewModel: GalleryViewModel by viewModel()

    GalleryList(viewModel = galleryViewModel, context = LocalContext.current,
        onNavigationRequested = { itemId ->
            navController.navigate("${Navigation.Route.GALLERY_LIST}/${itemId}")
        })
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun MviGalleryListScreen(navController: NavHostController) {
    val mviGalleryViewModel: MviGalleryViewModel by viewModel()
    LaunchedEffect(key1 = true){
        //this will cause to fetch first page
        mviGalleryViewModel.setEvent(GalleryContract.Event.OnInitialize)
    }
    MviGallery(
        state = mviGalleryViewModel.uiState.collectAsStateWithLifecycle(),
        onEventSent = { event -> mviGalleryViewModel.setEvent(event = event)  },
        context = LocalContext.current
    )
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun GalleryDetailScreen(navController: NavHostController, id: Int) {

    val galleryDetailViewModel: GalleryDetailViewModel by viewModel()
    LaunchedEffect(true) {
        galleryDetailViewModel.setEvent(GalleryDetailContract.Event.OnInitialize(id))
    }
    GalleryDetailView(
        state = galleryDetailViewModel.uiState.collectAsStateWithLifecycle(),
        effectFlow = galleryDetailViewModel.effect,
        onEventSent = { event -> galleryDetailViewModel.setEvent(event = event) },
        id = id
    )

}
