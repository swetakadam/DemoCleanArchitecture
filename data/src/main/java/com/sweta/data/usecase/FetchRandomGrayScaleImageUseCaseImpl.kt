package com.sweta.data.usecase

import com.sweta.domain.models.GalleryImage
import com.sweta.domain.repositories.GalleryRepository
import com.sweta.domain.usecases.FetchRandomGrayScaleImageUseCase
import com.sweta.domain.utils.ResultWrapper

class FetchRandomGrayScaleImageUseCaseImpl(
    private val galleryRepository: GalleryRepository
):FetchRandomGrayScaleImageUseCase {
    override suspend fun invoke(): ResultWrapper<GalleryImage> {
        return galleryRepository.getRandomGrayScaleImage()
    }
}