package com.sweta.data.network.mappers

import com.sweta.data.network.models.ApiGalleryItem
import com.sweta.domain.models.GalleryImage
import retrofit2.http.Url
import java.lang.Math.random
import kotlin.random.Random

class ApiGalleryItemMapper : ApiMapper<ApiGalleryItem?,GalleryImage> {
    override fun mapToDomain(apiEntity: ApiGalleryItem?): GalleryImage {

        return GalleryImage(
            id = apiEntity?.id ?: -1,
            author = apiEntity?.author ?: "",
            width = apiEntity?.width ?: -1,
            height = apiEntity?.height ?: -1,
            url = apiEntity?.url  ?: "",
            downloadUrl = apiEntity?.downloadUrl ?: "",
            grayscaleUrl =  apiEntity?.downloadUrl?.plus("?grayscale") ?: "",
            //blur values acceptable 1..10
            blurUrl = apiEntity?.downloadUrl?.plus("?blur=${Random.nextInt(1, 11)}") ?: "",
            blur = apiEntity?.blur,
            isGrayScale = apiEntity?.isGrayscale ?: false
        )

    }

}