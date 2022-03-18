package com.sweta.domain.usecases

import androidx.paging.PagingData
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FetchRandomGrayScaleImageUseCase {
    suspend operator fun invoke(): ResultWrapper<GalleryImage>
}