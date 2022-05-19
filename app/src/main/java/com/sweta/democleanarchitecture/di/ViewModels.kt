package com.sweta.democleanarchitecture.di

import com.sweta.democleanarchitecture.ui.gallery.GalleryViewModel
import com.sweta.democleanarchitecture.ui.galleryDetails.GalleryDetailViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
   // you can pass context using androidContext()
    viewModel { GalleryViewModel(get()) }
    viewModel { GalleryDetailViewModel(get()) }

}