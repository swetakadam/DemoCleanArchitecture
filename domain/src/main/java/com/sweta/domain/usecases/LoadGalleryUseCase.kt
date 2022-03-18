package com.sweta.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.repositories.GalleryRepository
import kotlinx.coroutines.flow.Flow

interface LoadGalleryUseCase {
    operator fun invoke(): Flow<PagingData<GalleryImage>>
}