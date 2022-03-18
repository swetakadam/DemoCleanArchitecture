package com.sweta.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sweta.data.GalleryApi
import com.sweta.data.network.getErrorResultWrapper
import com.sweta.data.network.interceptors.NetworkUnavailableException
import com.sweta.data.network.mappers.ApiGalleryItemMapper
import com.sweta.data.network.mappers.ApiMapper
import com.sweta.data.network.models.ApiGalleryItem
import com.sweta.data.paging.GalleryDataSource
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.repositories.GalleryRepository
import com.sweta.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

const val PAGE_SIZE = 10

class GalleryRepositoryImpl(
    private val pager: Pager<Int, GalleryImage>,
    private val galleryApi: GalleryApi,
    private val galleryItemMapper: ApiMapper<ApiGalleryItem?, GalleryImage>
) : GalleryRepository {


    override fun loadGalleryWithPagination(): Flow<PagingData<GalleryImage>> {
        return pager.flow
    }

    override suspend fun getRandomGrayScaleImage(): ResultWrapper<GalleryImage> {

        return try {
            val response = galleryApi.getRandomGrayScaleImage()

            if (response.isSuccessful) {
                val body = response.body()
                body?.run {
                    ResultWrapper.Success(galleryItemMapper.mapToDomain(this))
                } ?: run {
                    ResultWrapper.Success(null)
                }
            } else {
                response.getErrorResultWrapper()
            }

        } catch (ex: NetworkUnavailableException) {
            ResultWrapper.NetworkError
        } catch (ex: Exception) {
            ResultWrapper.GenericError(Exception("Api Error"))
        }


    }
}