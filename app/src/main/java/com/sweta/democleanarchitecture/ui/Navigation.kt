package com.sweta.democleanarchitecture.ui

import com.sweta.democleanarchitecture.ui.Navigation.Arg.GALLERY_DETAIL_ID

object Navigation {

    object Arg {
        const val GALLERY_DETAIL_ID = "galleryDetailId"
    }

    object Route {
        const val GALLERY_LIST = "galleryList"
        const val GALLERY_DETAILS = "$GALLERY_LIST/{$GALLERY_DETAIL_ID}"
    }

}