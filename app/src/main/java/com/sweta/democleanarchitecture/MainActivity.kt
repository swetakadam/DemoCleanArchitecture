package com.sweta.democleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sweta.democleanarchitecture.ui.Navigation
import com.sweta.democleanarchitecture.ui.gallery.GalleryList
import com.sweta.democleanarchitecture.ui.gallery.GalleryViewModel
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailContract
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailView
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailViewModel
import com.sweta.democleanarchitecture.ui.theme.DemoCleanArchitectureTheme
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    //private val galleryViewModel:GalleryViewModel by viewModel()
    //private val galleryDetailViewModel:GalleryDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoCleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    //GalleryList(viewModel = galleryViewModel, context = this)
                    DemoAppNavigation()
                }
            }
        }
    }
}

@Composable
fun DemoAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Navigation.Route.GALLERY_LIST) {
        composable(route = Navigation.Route.GALLERY_LIST) {
            GalleryListScreen(navController)
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

@Composable
private fun GalleryDetailScreen(navController: NavHostController, id: Int) {

    val galleryDetailViewModel: GalleryDetailViewModel by viewModel()
    LaunchedEffect(true) {
        galleryDetailViewModel.setEvent(GalleryDetailContract.Event.OnInitialize(id))
    }
    GalleryDetailView(
        state = galleryDetailViewModel.uiState.value,
        effectFlow = galleryDetailViewModel.effect,
        onEventSent = { event -> galleryDetailViewModel.setEvent(event = event) },
        id = id
    )

}
