package com.sweta.domain.usecases

import androidx.paging.PagingData
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FetchGalleryItemDetailsUseCase {
    suspend operator fun invoke(id:Int): ResultWrapper<GalleryImage>
}