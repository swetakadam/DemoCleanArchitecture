package com.sweta.data.usecase

import com.sweta.domain.models.GalleryImage
import com.sweta.domain.repositories.GalleryRepository
import com.sweta.domain.usecases.FetchGalleryItemDetailsUseCase
import com.sweta.domain.usecases.FetchRandomGrayScaleImageUseCase
import com.sweta.domain.utils.ResultWrapper

class FetchGalleryItemDetailsUseCaseImpl(
    private val galleryRepository: GalleryRepository,
): FetchGalleryItemDetailsUseCase {
    override suspend fun invoke(id: Int): ResultWrapper<GalleryImage> {
        return galleryRepository.getGalleryItemDetails(id)
    }
}