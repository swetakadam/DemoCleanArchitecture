package com.sweta.data.di

import com.sweta.data.usecase.FetchGalleryItemDetailsUseCaseImpl
import com.sweta.data.usecase.FetchRandomGrayScaleImageUseCaseImpl
import com.sweta.data.usecase.LoadGalleryUseCaseImpl
import com.sweta.domain.usecases.FetchGalleryItemDetailsUseCase
import com.sweta.domain.usecases.FetchRandomGrayScaleImageUseCase
import com.sweta.domain.usecases.LoadGalleryUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single<LoadGalleryUseCase>{ LoadGalleryUseCaseImpl(get())}
    single<FetchRandomGrayScaleImageUseCase> {FetchRandomGrayScaleImageUseCaseImpl(get())}
    single<FetchGalleryItemDetailsUseCase> {FetchGalleryItemDetailsUseCaseImpl(get())}
}