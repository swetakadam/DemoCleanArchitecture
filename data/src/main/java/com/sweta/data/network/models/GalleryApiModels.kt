package com.sweta.data.network.models

import com.google.gson.annotations.SerializedName
import com.sweta.domain.models.GalleryImage

data class GalleryLoadRequest(
    val page:Int,
    val limit:Int = 10
)

data class ApiGalleryItem(
    val id: Int,
    val author: String?,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url") val downloadUrl: String
)
