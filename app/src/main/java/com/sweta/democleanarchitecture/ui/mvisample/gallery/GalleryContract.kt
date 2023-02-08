package com.sweta.democleanarchitecture.ui.mvisample.gallery

import androidx.paging.PagingData
import com.sweta.democleanarchitecture.ui.base.UiEffect
import com.sweta.democleanarchitecture.ui.base.UiEvent
import com.sweta.democleanarchitecture.ui.base.UiState
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailContract
import com.sweta.domain.models.GalleryImage
import kotlinx.coroutines.flow.Flow

class GalleryContract {

    sealed class Event: UiEvent {
        object OnInitialize: Event()
        object OnFilterClicked: Event()
        data class OnItemClicked(val id:Int):Event()
        object UpdateNavigateState : Event()
    }

    // Ui View States
    data class State(
        val galleryState:GalleryState,
        val filterState:GalleryFilterState,
        val navigationState:NavigationState
    ):UiState

    sealed class GalleryFilterState {
        data class FilterValues(val isGrayScale:Boolean, val blurAmount:Int?):GalleryFilterState()
    }
    sealed class GalleryState {
        object Idle : GalleryState()
        object Loading : GalleryState()
        data class Success(val galleryFlow: Flow<PagingData<GalleryImage>>) : GalleryState()
        object Error : GalleryState()
    }


    // View State that related to Navigation
    sealed class NavigationState {
        object Idle : NavigationState()
        object NavigateBack : NavigationState()
        object NavigateToFilterScreen: NavigationState()
    }

    //deprecated
    sealed class Effect : UiEffect {

    }
}