package com.sweta.democleanarchitecture.ui.mvisample.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sweta.democleanarchitecture.ui.base.BaseViewModel
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailContract
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.usecases.LoadGalleryUseCase
import kotlinx.coroutines.flow.Flow

class MviGalleryViewModel(private val galleryUseCase: LoadGalleryUseCase) :


    BaseViewModel<GalleryContract.Event, GalleryContract.State, GalleryContract.Effect>() {


    private lateinit var gallery: Flow<PagingData<GalleryImage>>
    private lateinit var  filters: Filters

    override fun createInitialState(): GalleryContract.State {
        //gallery = galleryUseCase.invoke().cachedIn(viewModelScope)
        filters = Filters(isGrayScale = false, blurValue = null)
        return GalleryContract.State(
            GalleryContract.GalleryState.Idle,
            GalleryContract.GalleryFilterState.FilterValues(filters.isGrayScale, filters.blurValue),
            GalleryContract.NavigationState.Idle
        )

    }

    override fun handleEvent(event: GalleryContract.Event) {
        when(event) {

            is GalleryContract.Event.OnInitialize -> {
                gallery = galleryUseCase.invoke().cachedIn(viewModelScope)
                setState {
                    copy(
                        galleryState = GalleryContract.GalleryState.Success(gallery) ,
                        filterState =  GalleryContract.GalleryFilterState.FilterValues(filters.isGrayScale, filters.blurValue),
                        navigationState = GalleryContract.NavigationState.Idle
                        )
                }

            }

            is GalleryContract.Event.OnFilterClicked -> {

            }
            is GalleryContract.Event.OnItemClicked -> {

            }
            else -> {

            }
        }
    }

}

data class Filters(
    val isGrayScale: Boolean = false,
    val blurValue: Int? = null
)