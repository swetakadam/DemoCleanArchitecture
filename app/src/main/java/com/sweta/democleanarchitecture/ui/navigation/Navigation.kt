package com.sweta.democleanarchitecture.ui.navigation

import com.sweta.democleanarchitecture.ui.navigation.Navigation.Arg.GALLERY_DETAIL_ID

object Navigation {

    object Arg {
        const val GALLERY_DETAIL_ID = "galleryDetailId"
    }

    object Route {
        const val GALLERY_LIST = "galleryList"
        const val GALLERY_DETAILS = "$GALLERY_LIST/{$GALLERY_DETAIL_ID}"
        const val MAP_VIEW = "map"
        const val GEOMARKER = "geomarker"
    }

}