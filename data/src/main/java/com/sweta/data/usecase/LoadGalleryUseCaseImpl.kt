package com.sweta.data.usecase

import androidx.paging.PagingData
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.repositories.GalleryRepository
import com.sweta.domain.usecases.LoadGalleryUseCase
import kotlinx.coroutines.flow.Flow

class LoadGalleryUseCaseImpl(
    private val galleryRepository: GalleryRepository
):LoadGalleryUseCase {
    override fun invoke(): Flow<PagingData<GalleryImage>> {
        return galleryRepository.loadGalleryWithPagination()
    }
}