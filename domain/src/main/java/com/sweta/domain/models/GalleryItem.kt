package com.sweta.domain.models

data class GalleryImage(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val blurUrl: String,
    val grayscaleUrl : String,
    val downloadUrl : String
)