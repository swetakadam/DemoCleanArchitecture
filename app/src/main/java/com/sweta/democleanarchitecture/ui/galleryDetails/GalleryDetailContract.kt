package com.sweta.democleanarchitecture.ui.galleryDetails

import com.sweta.democleanarchitecture.ui.base.UiEffect
import com.sweta.democleanarchitecture.ui.base.UiEvent
import com.sweta.democleanarchitecture.ui.base.UiState
import com.sweta.domain.models.GalleryImage

class GalleryDetailContract {

    // Events that user performed
    sealed class Event : UiEvent {
        object OnFavoriteClicked: Event()
        object OnSharedClicked: Event()
        data class OnInitialize(val id:Int): Event()
    }

    // Ui View States
    data class State(
        val galleryDetailState: GalleryDetailState
    ) : UiState

    // View State that related to Random Number
    sealed class GalleryDetailState {
        object Idle : GalleryDetailState()
        object Loading : GalleryDetailState()
        data class Success(val image : GalleryImage) :GalleryDetailState()
        object Error: GalleryDetailState()
    }

    sealed class FavoriteState {
        object Success:GalleryDetailState()
        object Error: GalleryDetailState()
    }

    // Side effects
    sealed class Effect : UiEffect {
        data class  ShowToast(val message:String) : Effect()
        //navigation
        sealed class Navigation: Effect(){
            data class ToAnotherPage(val someData:String): Navigation()
        }
    }

}