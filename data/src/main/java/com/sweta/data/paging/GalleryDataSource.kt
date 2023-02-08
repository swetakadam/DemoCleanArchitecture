package com.sweta.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sweta.data.GalleryApi
import com.sweta.data.network.ApiException
import com.sweta.data.network.mappers.ApiMapper
import com.sweta.data.network.models.ApiGalleryItem
import com.sweta.domain.models.GalleryImage
import retrofit2.HttpException
import java.io.IOException

class GalleryDataSource constructor(
    private val galleryApi: GalleryApi,
    private val apiGalleryItemMapper: ApiMapper<ApiGalleryItem?, GalleryImage>
) : PagingSource<Int, GalleryImage>() {
    override fun getRefreshKey(state: PagingState<Int, GalleryImage>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryImage> {
        return try {

            val nextPage = params.key ?: 1
            val galleryResponse = galleryApi.getGalleryWithPagination(
                page = nextPage,
                limit = 10
            )
            val gallery = galleryResponse.body()
            gallery?.run {

                val nextKey = if (this.isEmpty()) null else nextPage.plus(1)
                val prevKey = if (nextPage == 1) null else nextPage - 1

                return if (params.key == null && gallery.isEmpty()) {
                    LoadResult.Error(ApiException("Gallery Fetch Error"))
                } else {
                    LoadResult.Page(
                        data = this.map {
                            it.blur = 7
                            it.isGrayscale = true
                            apiGalleryItemMapper.mapToDomain(it)
                                        },


                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
            } ?: run {
                throw ApiException("Gallery Fetch Error")
            }


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}