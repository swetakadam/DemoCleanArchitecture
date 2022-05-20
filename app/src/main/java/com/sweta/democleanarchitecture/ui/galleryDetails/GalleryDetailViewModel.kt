package com.sweta.democleanarchitecture.ui.galleryDetails

import androidx.lifecycle.viewModelScope
import com.sweta.democleanarchitecture.ui.base.BaseViewModel
import com.sweta.domain.usecases.FetchGalleryItemDetailsUseCase
import com.sweta.domain.usecases.LoadGalleryUseCase
import com.sweta.domain.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.viewModel
import java.lang.Exception

/**
 * MVI example
 * Influenced by
 * https://proandroiddev.com/mvi-architecture-with-kotlin-flows-and-channels-d36820b2028d
 * flows to collect in jetpack compose
 * https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
 */
class GalleryDetailViewModel(private val galleryDetailUseCase: FetchGalleryItemDetailsUseCase) :
    BaseViewModel<GalleryDetailContract.Event, GalleryDetailContract.State, GalleryDetailContract.Effect>() {


    override fun createInitialState(): GalleryDetailContract.State {
        return GalleryDetailContract.State(GalleryDetailContract.GalleryDetailState.Idle)
    }


    override fun handleEvent(event: GalleryDetailContract.Event) {
        when (event) {

            is GalleryDetailContract.Event.OnFavoriteClicked -> {
                onFavoriteClicked()
            }

            is GalleryDetailContract.Event.OnSharedClicked -> {
                onSharedClicked()
            }

            is GalleryDetailContract.Event.OnInitialize -> {
                fetchGalleryDetails(event.id)
            }


        }
    }

    private fun fetchGalleryDetails(id: Int) {
        viewModelScope.launch {


            setState { copy(galleryDetailState = GalleryDetailContract.GalleryDetailState.Loading) }
            delay(2000)
            when (val result = withContext(Dispatchers.IO) {
                galleryDetailUseCase(id) // galleryDetailUseCase.invoke(id)
            }) {
                is ResultWrapper.Success -> {
                    val galleryDetail = result.data!! //avoid double bang
                    setState {
                        copy(
                            galleryDetailState = GalleryDetailContract.GalleryDetailState.Success(
                                galleryDetail
                            )
                        )
                    }
                }
                is ResultWrapper.GenericError -> {
                    val errorMessage = result.exception?.localizedMessage ?: "Generic Error"
                    setEffect { GalleryDetailContract.Effect.ShowToast(errorMessage) }
                }

                is ResultWrapper.NetworkError -> {
                    val errorMessage = "No Internet"
                    setEffect { GalleryDetailContract.Effect.ShowToast(errorMessage) }
                }
                is ResultWrapper.AuthError -> {
                    val errorMessage = "Auth Error"
                    setEffect { GalleryDetailContract.Effect.ShowToast(errorMessage) }
                }
            }

        }

    }

    private fun onFavoriteClicked() {

        viewModelScope.launch {
            try {
                //add delay to simulate network call
                delay(2000)
                //set state ...

            } catch (exception: Exception) {
                //set effect ...
            }
        }

    }

    private fun onSharedClicked() {
        //send analytics ...
    }
}