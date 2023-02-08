package com.sweta.democleanarchitecture.ui.gallery

import android.util.Range
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.usecases.LoadGalleryUseCase
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(private val galleryUseCase: LoadGalleryUseCase) : ViewModel(){

    val gallery: Flow<PagingData<GalleryImage>> = galleryUseCase.invoke().cachedIn(viewModelScope)

}